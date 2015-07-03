package com.payit.manager.services.funding

import com.payit.manager.data.daos.FundingAccountDAO
import com.payit.manager.models.{Application, FundingAccount}
import com.payit.manager.services.funding.dtos.{NewFundingAccount, FundingAccountDetails}

class AddFundingAccountService(fundingAccountDAO: FundingAccountDAO) {

  def add(app: Application, newFundingAccount: NewFundingAccount): FundingAccountDetails = {
    val fundingAccount = fundingAccountDAO.insert(FundingAccount(
      newFundingAccount.name,
      newFundingAccount.fundingMethod,
      newFundingAccount.currency,
      newFundingAccount.country,
      app.id
    ))
    FundingAccountDetails(fundingAccount.id.toString, fundingAccount.name)
  }

}
