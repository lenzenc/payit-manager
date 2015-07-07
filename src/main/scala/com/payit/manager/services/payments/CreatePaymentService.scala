package com.payit.manager.services.payments

import com.mongodb.casbah.Imports._
import com.payit.manager.data.daos.{FundingAccountDAO, BankAccountDAO, PaymentDAO}
import com.payit.manager.models.{BankAccount, Application, Payment}
import com.payit.manager.services.payments.dtos.{Beneficiary, PaymentDetails, PaymentRequest}

class CreatePaymentService(
  paymentDAO: PaymentDAO,
  bankAccountDAO: BankAccountDAO,
  fundingAccountDAO: FundingAccountDAO)
{

  def create(app: Application, paymentRequest: PaymentRequest): PaymentDetails = {

    val payment = paymentDAO.findByPaymentId(app.id, paymentRequest.paymentId)
    if (payment.isDefined) return details(payment.get)

    val fundingAccount = fundingAccountDAO.findById(
      new ObjectId(paymentRequest.fundingId)).getOrElse(
        sys.error(s"Unable to find funding account with funding id: ${paymentRequest.fundingId}")
      )

    val bankAccount = bankAccountDAO.findByAccountId(
      app.id,
      paymentRequest.beneficiary.accountId).getOrElse(createBankAccount(
        app,
        paymentRequest.beneficiary))

    details(paymentDAO.insert(Payment(
      fundingAccount.id,
      bankAccount.id,
      paymentRequest.amount,
      paymentRequest.paymentId,
      app.id
    )))

  }

  private def details(payment: Payment): PaymentDetails = PaymentDetails(
    payment.id.toString,
    payment.paymentId,
    payment.amount)

  def createBankAccount(app: Application, beneficiary: Beneficiary) = bankAccountDAO.insert(BankAccount(
    name = beneficiary.name,
    country = beneficiary.country,
    currency = beneficiary.currency,
    accountType = BankAccount.AcctType.withName(beneficiary.bankAccountType.toString),
    accountNumber = beneficiary.accountNumber,
    routingNumber = beneficiary.routingNumber,
    accountId = beneficiary.accountId,
    applicationId = app.id
  ))

}
