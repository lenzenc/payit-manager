package com.payit.manager.services.funding

import com.payit.manager.data.daos.FundingAccountDAO
import com.payit.manager.models.FundingAccount
import com.payit.manager.services.funding.dtos.{NewFundingAccount, FundingAccountDetails}

class AddFundingAccountService(fundingAccountDAO: FundingAccountDAO) {

  def add(newFundingAccount: NewFundingAccount): FundingAccountDetails = {
    val fundingAccount = fundingAccountDAO.insert(FundingAccount(
      newFundingAccount.name,
      newFundingAccount.fundingMethod,
      newFundingAccount.currency,
      newFundingAccount.country
    ))
    FundingAccountDetails(fundingAccount.externalRef.toString, fundingAccount.name
    )
  }

}
