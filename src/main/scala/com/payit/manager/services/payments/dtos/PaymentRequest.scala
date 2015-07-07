package com.payit.manager.services.payments.dtos

case class PaymentRequest(
                           paymentId: String,
                           fundingId: String,
                           beneficiary: Beneficiary,
                           amount: BigDecimal)

