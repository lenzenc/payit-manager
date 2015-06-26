package com.payit.manager.data.mappers

import com.mongodb.DBObject
import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

import com.payit.manager.models.FundingAccount
import org.joda.time.DateTime

class FundingAccountMapper extends MongoObjectMapper[FundingAccount] {

  import FundingAccountMapper._

  def asDBObject(fundingAccount: FundingAccount): DBObject = {
    MongoDBObject(
      Id -> fundingAccount.id,
      Timestamps -> MongoDBObject(
        CreatedAt -> fundingAccount.timestamps.createdAt,
        UpdatedAt -> fundingAccount.timestamps.updatedAt
      ),
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
      externalRef = dbo.as[ObjectId](ExternalRef),
      fundingMethod = dbo.as[FundingMethod](FundingMethod),
      currency = dbo.as[Currency](Currency),
      country = dbo.as[Country](Country),
      journalEntries = Seq(),
      timestamps = com.payit.components.core.models.Timestamps(
        createdAt = dbo.as[DBObject](Timestamps).as[DateTime](CreatedAt),
        updatedAt = dbo.as[DBObject](Timestamps).as[DateTime](UpdatedAt)
      ),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object FundingAccountMapper extends CommonFields {

  val FundingMethod = "fundingMethod"

}
