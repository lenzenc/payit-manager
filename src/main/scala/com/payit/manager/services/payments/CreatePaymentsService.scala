package com.payit.manager.services.payments

import com.payit.manager.services.payments.dtos.{PaymentDetails, PaymentRequest}

class CreatePaymentsService {

  def create(paymentRequest: PaymentRequest): PaymentDetails = PaymentDetails("serrew", "dsfdsfds", BigDecimal(44.44))

}
