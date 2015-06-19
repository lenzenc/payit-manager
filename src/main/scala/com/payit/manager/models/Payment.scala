package com.payit.manager.models

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.components.mongodb.models.MongoModel

case class Payment(
                    amount: BigDecimal,
                    fundingAccountId: ObjectId,
                    externalRef: ObjectId = new ObjectId,
                    id: ObjectId = new ObjectId)
extends MongoModel[Payment]

object Payment extends MongoObjectMapper[Payment] {

  val Amount = "amount"
  val FundingAccountId = "fundingAccountId"

  def asDBObject(payment: Payment): DBObject = {
    MongoDBObject(
      Id -> payment.id,
      ExternalRef -> payment.externalRef,
      Amount -> payment.amount,
      FundingAccountId -> payment.fundingAccountId
    )
  }

  def fromDBObject(dbo: DBObject): Payment = {
    Payment(
      amount = dbo.as[BigDecimal](Amount),
      externalRef = dbo.as[ObjectId](ExternalRef),
      fundingAccountId = dbo.as[ObjectId](FundingAccountId),
      id = dbo.as[ObjectId](Id)
    )
  }

}
