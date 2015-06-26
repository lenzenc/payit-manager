package com.payit.manager.data.mappers

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.data.daos.{BankAccountDAO, FundingAccountDAO}
import com.payit.manager.models.Payment

class PaymentMapper(
                     fundingAccountDAO: FundingAccountDAO,
                     bankAccountDAO: BankAccountDAO)
  extends MongoObjectMapper[Payment]
{

  import PaymentMapper._

  def asDBObject(payment: Payment): DBObject = {
    MongoDBObject(
      Id -> payment.id,
      Timestamps -> timestamps(payment),
      ExternalRef -> payment.externalRef,
      Amount -> payment.amount,
      FundingAccountId -> payment.fundingAccount.id,
      BeneficiaryAccountId -> payment.beneficiaryAccount.id
    )
  }

  def fromDBObject(dbo: DBObject): Payment = {
    Payment(
      amount = dbo.as[BigDecimal](Amount),
      externalRef = dbo.as[ObjectId](ExternalRef),
      fundingAccount = fundingAccountDAO.findById(dbo.as[ObjectId](FundingAccountId)).get,
      beneficiaryAccount = bankAccountDAO.findById(dbo.as[ObjectId](BeneficiaryAccountId)).get,
      timestamps = timestamps(dbo),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object PaymentMapper extends CommonFields {

  val Amount = "amount"
  val FundingAccountId = "fundingAccountId"
  val BeneficiaryAccountId = "beneficiaryAccountId"

}
