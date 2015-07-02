package com.payit.manager.data.mappers

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.BankAccount
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

class BankAccountMapper extends MongoObjectMapper[BankAccount] {

  import BankAccountMapper._

  def asDBObject(bankAccount: BankAccount): DBObject = {
    MongoDBObject(
      Id -> bankAccount.id,
      Timestamps -> timestamps(bankAccount),
      Name -> bankAccount.name,
      Country -> bankAccount.country,
      Currency -> bankAccount.currency,
      AccountType -> bankAccount.accountType.toString(),
      AccountNumber -> bankAccount.accountNumber,
      RoutingNumber -> bankAccount.routingNumber,
      AccountId -> bankAccount.accountId,
      ApplicationId -> bankAccount.applicationId
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
      accountId = dbo.as[String](AccountId),
      applicationId = dbo.as[ObjectId](ApplicationId),
      timestamps = timestamps(dbo),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object BankAccountMapper extends CommonFields {

  val AccountType = "accountType"
  val AccountNumber = "accountNumber"
  val RoutingNumber = "routingNumber"
  val AccountId = "accountId"

}
