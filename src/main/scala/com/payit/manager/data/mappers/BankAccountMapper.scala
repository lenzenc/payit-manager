package com.payit.manager.data.mappers

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.BankAccount
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

class BankAccountMapper extends MongoObjectMapper[BankAccount] {

  val AccountType = "accountType"
  val AccountNumber = "accountNumber"
  val RoutingNumber = "routingNumber"

  import BankAccountMapper._

  def asDBObject(bankAccount: BankAccount): DBObject = {
    MongoDBObject(
      Id -> bankAccount.id,
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
      id = dbo.as[ObjectId](Id)
    )
  }

}

object BankAccountMapper extends CommonFields
