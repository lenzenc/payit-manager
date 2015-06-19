package com.payit.manager

import akka.actor.ActorSystem
import com.mongodb.casbah.Imports._
import com.payit.components.core.Configuration
import com.payit.components.mongodb.migrations.{ResetApplyMigrations, MongoMigrator}
import com.payit.manager.data.daos.{FundingAccountDAO, PartnerDAO}
import com.payit.manager.services.dtos.NewFundingAccount
import com.payit.manager.services.{AddFundingAccountService, GetPartnerDetailsService}
import spray.routing.SimpleRoutingApp

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

  implicit val system = ActorSystem("payit-manager")

  startServer(interface = "localhost", port = 9000) {
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
              addFundingAccountService.add(account)
            }
          }
        }
      }
    }
  }

}
