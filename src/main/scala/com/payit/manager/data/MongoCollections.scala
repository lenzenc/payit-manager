package com.payit.manager.data

object MongoCollections extends Enumeration {
  type MongoCollections = Value
  val Partners = Value("partners")
  val FundingAccounts = Value("funding_accounts")
  val Payments = Value("payments")
}
