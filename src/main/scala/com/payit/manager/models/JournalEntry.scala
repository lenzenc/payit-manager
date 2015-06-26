package com.payit.manager.models

import com.payit.manager.models.JournalEntry.EntryType.EntryType
import org.joda.time.DateTime

case class JournalEntry(
  entryDate: DateTime,
  beginningBalance: BigDecimal,
  entryType: EntryType,
  amount: BigDecimal,
  endingBalance: BigDecimal)

object JournalEntry {

  object EntryType extends Enumeration {
    type EntryType = Value
    val Debit = Value("DEBIT")
    val Credit = Value("CREDIT")
  }

}
