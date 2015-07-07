package com.payit.manager.services.payments.dtos

import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import com.payit.manager.services.payments.dtos.BankAccountType.BankAccountType

case class Beneficiary(
                        accountId: String,
                        name: String,
                        country: Country,
                        currency: Currency,
                        routingNumber: String,
                        accountNumber: String,
                        bankAccountType: BankAccountType)