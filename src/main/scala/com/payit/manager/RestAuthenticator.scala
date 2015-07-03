package com.payit.manager

import spray.routing.{AuthenticationFailedRejection, RequestContext}
import spray.routing.authentication.{Authentication, ContextAuthenticator}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object RestAuthenticator {

  object HeaderExtraction {

    type HeaderExtractor = RequestContext => Option[String]

    def header(name: String): HeaderExtractor = { context =>
      context.request.headers.find(_.name == name).map(_.value)
    }

  }

  class RestAuthenticator[T](
    extractor: HeaderExtraction.HeaderExtractor,
    authenticator: (String => Future[Option[T]]))
  extends ContextAuthenticator[T]
  {

    import spray.routing.AuthenticationFailedRejection._

    def apply(context: RequestContext): Future[Authentication[T]] = {
      extractor(context) match {
        case None => Future(Left(AuthenticationFailedRejection(CredentialsMissing, List())))
        case Some(token) => {
          authenticator(token) map {
            case Some(t) => Right(t)
            case None => Left(AuthenticationFailedRejection(CredentialsRejected, List()))
          }
        }
      }
    }

  }

  def apply[T](headerName: String)(authenticator: (String => Future[Option[T]])) = {
    def extractor = HeaderExtraction.header(headerName)
    new RestAuthenticator(extractor, authenticator)
  }

}
