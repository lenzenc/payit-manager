package com.payit.manager.models

import com.payit.manager.FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

case class FundingAccount(fundingMethod: FundingMethod, currency: Currency, country: Country, journalEntries: Seq[JournalEntry] = Seq())
