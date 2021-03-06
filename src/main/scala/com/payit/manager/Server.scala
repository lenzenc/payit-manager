package com.payit.manager

import akka.actor.ActorSystem
import com.mongodb.casbah.Imports._
import com.payit.components.core.Configuration
import com.payit.components.mongodb.migrations.{ApplyMigrations, ResetApplyMigrations, MongoMigrator}
import com.payit.manager.data.daos._
import com.payit.manager.models.Application
import com.payit.manager.services.GetPartnerDetailsService
import com.payit.manager.services.funding.AddFundingAccountService
import com.payit.manager.services.funding.dtos.NewFundingAccount
import com.payit.manager.services.payments.CreatePaymentService
import com.payit.manager.services.payments.dtos.{Beneficiary, PaymentRequest}
import spray.routing.{Directive1, SimpleRoutingApp}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object Server extends App with SimpleRoutingApp with JsonImplicits with DBMigrator with MongoDBSupport {

  println("Running PayIT Manager...")

  lazy val config = Configuration.load

  val migrationCommand = args match {
    case Array(a) if a.equals("reset") => ResetApplyMigrations
    case _ => ApplyMigrations
  }
  migrateDB(migrationCommand)

  val applicationDAO = new ApplicationDAO()
  val partnerDAO = new PartnerDAO()
  val fundingAccountDAO = new FundingAccountDAO()
  val bankAccountDAO = new BankAccountDAO()
  val paymentDAO = new PaymentDAO()

  val getPartnerDetailsService = new GetPartnerDetailsService(partnerDAO)
  val addFundingAccountService = new AddFundingAccountService(fundingAccountDAO)
  val createPaymentService = new CreatePaymentService(paymentDAO, bankAccountDAO, fundingAccountDAO)

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
        } ~
        path("payments") {
          decompressRequest() {
            entity(as[PaymentRequest]) { paymentRequest =>
              complete {
                createPaymentService.create(app, paymentRequest)
              }
            }
          }
        }
      }
    }
  }

}