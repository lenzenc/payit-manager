package com.payit.manager

import com.payit.manager.models._
import com.payit.manager.services.dtos.PartnerDetails
import com.payit.manager.services.funding.dtos.{NewFundingAccount, FundingAccountDetails}
import com.payit.manager.services.payments.dtos._
import spray.httpx.SprayJsonSupport
import spray.json._

trait JsonImplicits extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val currencyJsonFormat = jsonEnum(Currency)
  implicit val countryJsonFormat = jsonEnum(Country)
  implicit val bankAccountTypeJsonFormat = jsonEnum(BankAccount.AcctType)

  implicit val partnerDetailJsonFormat = jsonFormat2(PartnerDetails)
  implicit val addedFundingAccountJsonFormat = jsonFormat2(FundingAccountDetails)

  implicit val fundingMethodJsonFormat = jsonEnum(FundingMethod)
  implicit val newFundingAccountJsonFormat = jsonFormat4(NewFundingAccount)
  implicit val paymentDetailsJsonFormat = jsonFormat3(PaymentDetails)
  implicit val dtoBankAccountTypeJsonFormat = jsonEnum(BankAccountType)
  implicit val beneficiaryJsonFormat = jsonFormat7(Beneficiary)
  implicit val paymentRequestJsonFormat = jsonFormat4(PaymentRequest)

  def jsonEnum[T <: Enumeration](enu: T) = new JsonFormat[T#Value] {
    def write(obj: T#Value) = JsString(obj.toString)
    def read(json: JsValue) = json match {
      case JsString(txt) => enu.withName(txt)
      case something => throw new DeserializationException(
        s"Expected a value from enum $enu instead of $something"
      )
    }
  }

}