package com.payit.manager.data.mappers

import com.mongodb.DBObject
import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

import com.payit.manager.models.FundingAccount

class FundingAccountMapper extends MongoObjectMapper[FundingAccount] {

  import FundingAccountMapper._

  def asDBObject(fundingAccount: FundingAccount): DBObject = {
    MongoDBObject(
      Id -> fundingAccount.id,
      Timestamps -> timestamps(fundingAccount),
      Name -> fundingAccount.name,
      FundingMethod -> fundingAccount.fundingMethod.toString,
      Currency -> fundingAccount.currency.toString,
      Country -> fundingAccount.country.toString,
      ApplicationId -> fundingAccount.applicationId
    )
  }

  def fromDBObject(dbo: DBObject): FundingAccount = {
    FundingAccount(
      name = dbo.as[String](Name),
      fundingMethod = com.payit.manager.models.FundingMethod.withName(dbo.as[String](FundingMethod)),
      country = com.payit.manager.models.Country.withName(dbo.as[String](Country)),
      currency = com.payit.manager.models.Currency.withName(dbo.as[String](Currency)),
      journalEntries = Seq(),
      applicationId = dbo.as[ObjectId](ApplicationId),
      timestamps = timestamps(dbo),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object FundingAccountMapper extends CommonFields {

  val FundingMethod = "fundingMethod"

}
