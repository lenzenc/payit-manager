package com.payit.manager.data.mappers

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.dao.ModelMapper
import com.payit.manager.FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import com.payit.manager.models.FundingAccount

object FundingAccountMapper extends ModelMapper[FundingAccount] {

  val FundingMethod = "fundingMethod"
  val Currency = "currency"
  val Country = "country"

  def asDBObject(fundingAccount: FundingAccount): DBObject = {
    MongoDBObject(
      Id -> fundingAccount.id,
      Name -> fundingAccount.name,
      ExternalRef -> fundingAccount.externalRef,
      FundingMethod -> fundingAccount.fundingMethod.toString,
      Currency -> fundingAccount.currency.toString,
      Country -> fundingAccount.country.toString
    )
  }

  def fromDBObject(dbo: DBObject): FundingAccount = {
    FundingAccount(
      name = dbo.as[String](Name),
      externalRef = dbo.as[String](ExternalRef),
      fundingMethod = dbo.as[FundingMethod](FundingMethod),
      currency = dbo.as[Currency](Currency),
      country = dbo.as[Country](Country),
      journalEntries = Seq(),
      id = dbo.as[ObjectId](Id)
    )
  }

}
