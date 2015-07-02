package com.payit.manager.data.mappers

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.Payment

class PaymentMapper() extends MongoObjectMapper[Payment]
{

  import PaymentMapper._

  def asDBObject(payment: Payment): DBObject = {
    MongoDBObject(
      Id -> payment.id,
      Timestamps -> timestamps(payment),
      PaymentId -> payment.paymentId,
      Amount -> payment.amount,
      FundingAccountId -> payment.fundingAccountId,
      BeneficiaryAccountId -> payment.beneficiaryAccountId
    )
  }

  def fromDBObject(dbo: DBObject): Payment = {
    Payment(
      amount = dbo.as[BigDecimal](Amount),
      fundingAccountId = dbo.as[ObjectId](FundingAccountId),
      beneficiaryAccountId = dbo.as[ObjectId](BeneficiaryAccountId),
      paymentId = dbo.as[String](PaymentId),
      timestamps = timestamps(dbo),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object PaymentMapper extends CommonFields {

  val Amount = "amount"
  val FundingAccountId = "fundingAccountId"
  val BeneficiaryAccountId = "beneficiaryAccountId"
  val PaymentId = "paymentId"

}
