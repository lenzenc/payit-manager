package com.payit.manager.models

import com.mongodb.casbah.Imports._
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.models.MongoModel
import org.joda.time.DateTime

case class  Payment(
                    fundingAccountId: ObjectId,
                    beneficiaryAccountId: ObjectId,
                    amount: BigDecimal,
                    paymentId: String,
                    timestamps: Timestamps = Timestamps(),
                    id: ObjectId = new ObjectId)
extends MongoModel[Payment]
{
  def withUpdatedAt(updatedAt: DateTime): Payment = copy(timestamps = timestamps.updated(updatedAt))
}