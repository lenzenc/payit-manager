package com.payit.manager.models

import com.payit.manager.FundingMethod.FundingMethod

case class FundingAccount(fundingMethod: FundingMethod, journalEntries: Seq[JournalEntry])
