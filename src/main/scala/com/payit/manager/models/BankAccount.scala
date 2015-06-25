package com.payit.manager.models

import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.components.mongodb.models.MongoModel
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import com.mongodb.casbah.Imports._
import com.payit.manager.models.BankAcctType.BankAcctType

object BankAcctType extends Enumeration {
  type BankAcctType = Value
  val Checking = Value("CHECKING")
  val Savings = Value("SAVINGS")
}

case class BankAccount(
                        name: String,
                        country: Country,
                        currency: Currency,
                        accountType: BankAcctType,
                        accountNumber: String,
                        routingNumber: String,
                        externalRef: ObjectId = new ObjectId,
                        id: ObjectId = new ObjectId)
extends MongoModel[BankAccount]

object BankAccount extends MongoObjectMapper[BankAccount] {

  val AccountType = "accountType"
  val AccountNumber = "accountNumber"
  val RoutingNumber = "routingNumber"

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
      accountType = BankAcctType.withName(dbo.as[String](AccountType)),
      accountNumber = dbo.as[String](AccountNumber),
      routingNumber = dbo.as[String](RoutingNumber),
      externalRef = dbo.as[ObjectId](ExternalRef),
      id = dbo.as[ObjectId](Id)
    )
  }

}

