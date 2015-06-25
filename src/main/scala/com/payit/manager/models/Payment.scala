package com.payit.manager.models

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.models.MongoModel

case class Payment(
                    amount: BigDecimal,
                    fundingAccountId: ObjectId,
                    externalRef: ObjectId = new ObjectId,
                    id: ObjectId = new ObjectId)
extends MongoModel[Payment]