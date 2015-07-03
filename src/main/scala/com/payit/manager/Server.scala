package com.payit.manager

import akka.actor.ActorSystem
import com.mongodb.casbah.Imports._
import com.payit.components.core.Configuration
import com.payit.components.mongodb.migrations.{ResetApplyMigrations, MongoMigrator}
import com.payit.manager.data.daos.{ApplicationDAO, FundingAccountDAO, PartnerDAO}
import com.payit.manager.models.Application
import com.payit.manager.services.GetPartnerDetailsService
import com.payit.manager.services.funding.AddFundingAccountService
import com.payit.manager.services.funding.dtos.NewFundingAccount
import spray.routing.{Directive1, SimpleRoutingApp}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object Server extends App with SimpleRoutingApp with JsonImplicits {

  println("Running PayIT Manager...")

  val config = Configuration.load
  val migrator = new MongoMigrator("default", config)
  migrator.migrate(ResetApplyMigrations, "com.payit.manager.data.migrations")
  val client: MongoClient = MongoClient(
    config.getString("mongo.default.host").getOrElse("localhost"),
    config.getInt("mongo.default.port").getOrElse(27017))
  implicit val db: MongoDB = client(config.getString("mongo.default.dbname").get)
  val getPartnerDetailsService = new GetPartnerDetailsService(new PartnerDAO())
  val addFundingAccountService = new AddFundingAccountService(new FundingAccountDAO())
  val applicationDAO = new ApplicationDAO()

  val authenticator = RestAuthenticator[Application]("ApiKey") { apiKey =>
    Future(applicationDAO.findByApiKey(apiKey))
  }

  def auth: Directive1[Application] = authenticate(authenticator)

  implicit val system = ActorSystem("payit-manager")

  startServer(interface = "localhost", port = 9000) {
    auth { app =>
      get {
        path("partners") {
          complete {
            getPartnerDetailsService.list()
          }
        } ~
          path("partners" / Segment  ) { id =>
            complete {
              getPartnerDetailsService.get(id)
            }
          }
      } ~
      post {
        path("fundingaccounts") {
          decompressRequest() {
            entity(as[NewFundingAccount]) { account =>
              complete {
                addFundingAccountService.add(app, account)
              }
            }
          }
        }
      }
    }
  }

}