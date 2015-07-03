package com.payit.manager.services.payments

import com.payit.manager.models.{Application, Payment}
import com.payit.manager.services.payments.dtos.{PaymentDetails, PaymentRequest}

class CreatePaymentService() {

  def create(app: Application, paymentRequest: PaymentRequest): PaymentDetails = {

    // Check for duplicate paymentId
    // check for current bene account and add, or create
    // error if funding reference does not exist

    PaymentDetails("serrew", "dsfdsfds", BigDecimal(44.44))
  }

  private def details(payment: Payment): PaymentDetails = PaymentDetails(
    payment.id.toString,
    payment.paymentId,
    payment.amount)

}
