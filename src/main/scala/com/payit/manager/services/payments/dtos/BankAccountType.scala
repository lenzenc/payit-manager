package com.payit.manager.services.payments.dtos

object BankAccountType extends Enumeration {
  type BankAccountType = Value
  val Checking = Value("CHECKING")
  val Savings = Value("SAVINGS")
}
