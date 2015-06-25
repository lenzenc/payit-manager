package com.payit.manager.models

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