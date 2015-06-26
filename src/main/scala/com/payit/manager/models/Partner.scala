package com.payit.manager.models

import com.payit.components.mongodb.models.MongoModel
import com.mongodb.casbah.Imports._
import com.payit.manager.models.Partner.ExternalRef.ExternalRef


case class Partner(
                    name: String,
                    externalRef: ExternalRef,
                    id: ObjectId = new ObjectId)
  extends MongoModel[Partner]

object Partner {

  object ExternalRef extends Enumeration {
    type ExternalRef = Value
    val PartnerA = Value("PARTNERA")
    val PartnerB = Value("PARTNERB")
  }

}
