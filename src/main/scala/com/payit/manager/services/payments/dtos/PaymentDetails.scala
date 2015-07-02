package com.payit.manager.services.payments.dtos

case class PaymentDetails(paymentId: String, externalRef: String, amount: BigDecimal)
