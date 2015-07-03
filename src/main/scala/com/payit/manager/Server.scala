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
                addFundingAccountService.add(account)
              }
            }
          }
        }
      }      
    }
  }

}

//trait RestAuthenticator[U] extends ContextAuthenticator[U] {
//
//  type ParamExtractor = RequestContext => Map[String, String]
//
//  val keys: Seq[String]
//
//  val authenticator: Map[String, String] => Future[Option[U]]
//
//  val extractor: ParamExtractor = (ctx: RequestContext) => ctx.request.uri.query.toMap
//
//  def getChallengeHeaders(httpRequest: HttpRequest): List[HttpHeader] = Nil
//
//  implicit def executionContext: ExecutionContext
//
//  def apply(ctx: RequestContext): Future[Authentication[U]] = {
//    val queryParams = extractor(ctx)
//    authenticator(queryParams) map {
//      case Some(entity) => Right(entity)
//      case None => {
//        val cause = if (queryParams.isEmpty) CredentialsMissing else CredentialsRejected
//        Left(AuthenticationFailedRejection(cause, getChallengeHeaders(ctx.request)))
//      }
//    }
//  }
//
//}
//
//object AccessTokenHandler {
//
//  import scala.concurrent.ExecutionContext.Implicits.global
//
//  val defaultKeys = Seq("access_token")
//  val defaultAuthenticator = (params: Map[String, String]) => Future {
//    val accessToken = params.get(defaultKeys(0))
//    accessToken flatMap { token =>
//      None
//    }
//  }
//
//  case class AccessTokenAuthenticator(
//                                       val keys: Seq[String] = defaultKeys,
//                                       val authenticator: Map[String, String] => Future[Option[Application]] = defaultAuthenticator)
//  extends RestAuthenticator[Application]
//  {
//    import scala.concurrent.ExecutionContext.Implicits.global
//
//    def apply(): Directive1[Application] = authenticate(this)
//  }
//
//}

//object AccessTokenHandler {
//
//  case class AccessTokenAuthenticator(val keys: List[String] = defaultKeys,
//                                      val authenticator: Map[String, String] => Future[Option[User]] = defaultAuthenticator) extends RestAuthenticator[User] {
//    def apply(): Directive1[User] = authenticate(this)
//  }
//
//  val defaultKeys = List("access_token")
//  val defaultAuthenticator = (params: Map[String, String]) => Future {
//    val mayBeUser = params.get(defaultKeys(0))
//    mayBeUser flatMap { token =>
//      /*
//       * get user form database , replace None with proper method once database service is ready.
//       * getUserFromAccessToken(token)
//       */
//      None
//    }
//  }
//
//}
