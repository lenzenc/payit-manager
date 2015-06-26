package com.payit.manager.models

import FundingMethod.FundingMethod
import com.payit.manager.models.Country.Country
import com.payit.manager.models.Currency.Currency

case class PartnerFunding(country: Country, currency: Currency, fundingMethod: FundingMethod)
