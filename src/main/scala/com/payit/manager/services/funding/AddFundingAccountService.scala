package com.payit.manager.services.funding

import com.mongodb.casbah.Imports._
import com.payit.manager.data.daos.FundingAccountDAO
import com.payit.manager.models.FundingAccount
import com.payit.manager.services.funding.dtos.{NewFundingAccount, FundingAccountDetails}

class AddFundingAccountService(fundingAccountDAO: FundingAccountDAO) {

  // TODO: Pass Application to this method so that the correct applicationId can be added

  def add(newFundingAccount: NewFundingAccount): FundingAccountDetails = {
    val fundingAccount = fundingAccountDAO.insert(FundingAccount(
      newFundingAccount.name,
      newFundingAccount.fundingMethod,
      newFundingAccount.currency,
      newFundingAccount.country,
      new ObjectId
    ))
    FundingAccountDetails(fundingAccount.id.toString, fundingAccount.name
    )
  }

}
