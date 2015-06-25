package com.payit.manager.models

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.models.MongoModel

case class Payment(
                    fundingAccount: FundingAccount,
                    beneficiaryAccount: BankAccount,
                    externalRef: ObjectId = new ObjectId,
                    amount: BigDecimal,
                    id: ObjectId = new ObjectId)
extends MongoModel[Payment]