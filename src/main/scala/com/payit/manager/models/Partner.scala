package com.payit.manager.models

import com.payit.components.mongodb.models.MongoModel
import com.mongodb.casbah.Imports._
import com.payit.manager.models.PartnerExternalRef.PartnerExternalRef

object PartnerExternalRef extends Enumeration {
  type PartnerExternalRef = Value
  val PartnerA = Value("PARTNERA")
  val PartnerB = Value("PARTNERB")
}

case class Partner(name: String, externalRef: PartnerExternalRef, id: ObjectId = new ObjectId) extends MongoModel[Partner]
