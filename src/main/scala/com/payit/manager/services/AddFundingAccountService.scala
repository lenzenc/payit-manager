package com.payit.manager.services

import com.payit.manager.data.daos.FundingAccountDAO
import com.payit.manager.models.FundingAccount
import com.payit.manager.services.dtos.{AddedFundingAccount, NewFundingAccount}
import com.mongodb.casbah.Imports.ObjectId

class AddFundingAccountService(fundingAccountDAO: FundingAccountDAO) {

  def add(newFundingAccount: NewFundingAccount): AddedFundingAccount = {
    val fundingAccount = fundingAccountDAO.insert(FundingAccount(
      newFundingAccount.name,
      new ObjectId().toString,
      newFundingAccount.fundingMethod,
      newFundingAccount.currency,
      newFundingAccount.country
    ))
    AddedFundingAccount(fundingAccount.externalRef, fundingAccount.name
    )
  }

}
