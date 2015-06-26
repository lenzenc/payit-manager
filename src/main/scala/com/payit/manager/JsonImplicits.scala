package com.payit.manager

import com.payit.manager.models._
import com.payit.manager.services.dtos.{NewFundingAccount, AddedFundingAccount, PartnerDetail}
import spray.httpx.SprayJsonSupport
import spray.json._

trait JsonImplicits extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val currencyJsonFormat = jsonEnum(Currency)
  implicit val countryJsonFormat = jsonEnum(Country)
  implicit val partnerExternalRefJsonFormat = jsonEnum(Partner.ExternalRef)
  implicit val bankAccountTypeJsonFormat = jsonEnum(BankAccount.AcctType)

  implicit val partnerDetailJsonFormat = jsonFormat2(PartnerDetail)
  implicit val addedFundingAccountJsonFormat = jsonFormat2(AddedFundingAccount)

  implicit val fundingMethodJsonFormat = jsonEnum(FundingMethod)
  implicit val newFundingAccountJsonFormat = jsonFormat4(NewFundingAccount)

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
