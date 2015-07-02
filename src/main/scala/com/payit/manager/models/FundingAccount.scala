package com.payit.manager.models

import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.models.MongoModel
import FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import com.mongodb.casbah.Imports._
import org.joda.time.DateTime

case class FundingAccount(
                           name: String,
                           fundingMethod: FundingMethod,
                           currency: Currency,
                           country: Country,
                           journalEntries: Seq[JournalEntry] = Seq(),
                           timestamps: Timestamps = Timestamps(),
                           id: ObjectId = new ObjectId)
extends MongoModel[FundingAccount]
{
  def withUpdatedAt(updatedAt: DateTime): FundingAccount = copy(timestamps = timestamps.updated(updatedAt))
}