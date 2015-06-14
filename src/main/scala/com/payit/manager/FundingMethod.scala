package com.payit.manager

object FundingMethod extends Enumeration {
  type FundingMethod = Value
  val Debit = Value("DEBIT")
  val Push = Value("PUSH")

}