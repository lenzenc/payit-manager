package com.payit.manager.services.payments.dtos

case class PaymentRequest(
                           paymentId: String,
                           fundingRef: String,
                           beneficiary: Beneficiary,
                           amount: BigDecimal)
