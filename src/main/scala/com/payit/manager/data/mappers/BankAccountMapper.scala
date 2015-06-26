package com.payit.manager.data.mappers

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.BankAccount
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import org.joda.time.DateTime

class BankAccountMapper extends MongoObjectMapper[BankAccount] {

  val AccountType = "accountType"
  val AccountNumber = "accountNumber"
  val RoutingNumber = "routingNumber"

  import BankAccountMapper._

  def asDBObject(bankAccount: BankAccount): DBObject = {
    MongoDBObject(
      Id -> bankAccount.id,
      Timestamps -> MongoDBObject(
        CreatedAt -> bankAccount.timestamps.createdAt,
        UpdatedAt -> bankAccount.timestamps.updatedAt
      ),
      ExternalRef -> bankAccount.externalRef,
      Name -> bankAccount.name,
      Country -> bankAccount.country,
      Currency -> bankAccount.currency,
      AccountType -> bankAccount.accountType.toString(),
      AccountNumber -> bankAccount.accountNumber,
      RoutingNumber -> bankAccount.routingNumber
    )
  }

  def fromDBObject(dbo: DBObject): BankAccount = {
    BankAccount(
      name = dbo.as[String](Name),
      country = dbo.as[Country](Name),
      currency = dbo.as[Currency](Name),
      accountType = BankAccount.AcctType.withName(dbo.as[String](AccountType)),
      accountNumber = dbo.as[String](AccountNumber),
      routingNumber = dbo.as[String](RoutingNumber),
      externalRef = dbo.as[ObjectId](ExternalRef),
      timestamps = com.payit.components.core.models.Timestamps(
        createdAt = dbo.as[DBObject](Timestamps).as[DateTime](CreatedAt),
        updatedAt = dbo.as[DBObject](Timestamps).as[DateTime](UpdatedAt)
      ),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object BankAccountMapper extends CommonFields
