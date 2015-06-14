package com.payit.manager.models

import com.payit.manager.FundingMethod.FundingMethod

case class PartnerFunding(country: Country, currency: Currency, fundingMethod: FundingMethod)
