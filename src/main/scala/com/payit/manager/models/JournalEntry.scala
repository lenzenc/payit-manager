package com.payit.manager.models

import com.payit.manager.models.EntryType.EntryType
import org.joda.time.DateTime

object EntryType extends Enumeration {
  type EntryType = Value
  val Debit = Value("DEBIT")
  val Credit = Value("CREDIT")
}

case class JournalEntry(
  entryDate: DateTime,
  beginningBalance: BigDecimal,
  entryType: EntryType,
  amount: BigDecimal,
  endingBalance: BigDecimal)
