package com.payit.manager.models

import com.payit.manager.FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

case class PartnerFunding(country: Country, currency: Currency, fundingMethod: FundingMethod)
