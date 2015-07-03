package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.specs.DAOSpec
import com.payit.components.specs.SpecScope
import com.payit.manager.models.{Currency, Country, BankAccount}

class BankAccountDAOSpec extends DAOSpec {

  trait TestScope extends SpecScope {

    val bankAccountDAO = new BankAccountDAO()
    val applicationId = new ObjectId
    val accountId = new ObjectId().toString
    val id = new ObjectId
    bankAccountDAO.insert(BankAccount(
      "Test Bank Account",
      Country.USA,
      Currency.USD,
      BankAccount.AcctType.Checking,
      "090000000",
      "123456789",
      accountId,
      applicationId,
      Timestamps(),
      id
    ))

  }

  ".findByAccountId" >> {
    "when a record exists for a given account id" >> {
      "it should return expected BankAccount" in new TestScope {
        bankAccountDAO.findByAccountId(applicationId, accountId) must beSome.like { case b => b.id must_== id }
      }
    }
    "when no record exists for a given account id" >> {
      "it should return None" in new TestScope {
        bankAccountDAO.findByAccountId(applicationId, "doesnotexist") must beNone
      }
    }
  }

}
