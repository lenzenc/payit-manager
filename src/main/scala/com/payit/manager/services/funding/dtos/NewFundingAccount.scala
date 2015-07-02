package com.payit.manager.services.funding.dtos

import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency
import com.payit.manager.models.FundingMethod.FundingMethod

case class NewFundingAccount(
                              name: String,
                              fundingMethod: FundingMethod,
                              currency: Currency,
                              country: Country)
