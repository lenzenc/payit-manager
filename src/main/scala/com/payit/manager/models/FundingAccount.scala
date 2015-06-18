package com.payit.manager.models

import com.payit.components.mongodb.models.MongoModel
import com.payit.manager.FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import com.mongodb.casbah.Imports.ObjectId

case class FundingAccount(
                           name: String,
                           externalRef: String,
                           fundingMethod: FundingMethod,
                           currency: Currency,
                           country: Country,
                           journalEntries: Seq[JournalEntry] = Seq(),
                           id: ObjectId = new ObjectId)
extends MongoModel[FundingAccount]
