package com.payit.manager.models

import com.payit.components.mongodb.models.MongoModel
import com.payit.manager.models.BankAccount.AcctType.AcctType
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import com.mongodb.casbah.Imports._

case class BankAccount(
                        name: String,
                        country: Country,
                        currency: Currency,
                        accountType: AcctType,
                        accountNumber: String,
                        routingNumber: String,
                        externalRef: ObjectId = new ObjectId,
                        id: ObjectId = new ObjectId)
extends MongoModel[BankAccount]

object BankAccount {

  object AcctType extends Enumeration {
    type AcctType = Value
    val Checking = Value("CHECKING")
    val Savings = Value("SAVINGS")
  }

}