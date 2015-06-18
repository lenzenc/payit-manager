package com.payit.manager.services.dtos

import com.payit.manager.FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

case class NewFundingAccount(
                              name: String,
                              fundingMethod: FundingMethod,
                              currency: Currency,
                              country: Country)
