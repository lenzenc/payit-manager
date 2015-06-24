package com.payit.manager.models

import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.components.mongodb.models.MongoModel
import com.mongodb.casbah.Imports._
import com.payit.manager.models.PartnerExternalRef.PartnerExternalRef

object PartnerExternalRef extends Enumeration {
  type PartnerExternalRef = Value
  val PartnerA = Value("PARTNERA")
  val PartnerB = Value("PARTNERB")
}

case class Partner(name: String, externalRef: PartnerExternalRef, id: ObjectId = new ObjectId) extends MongoModel[Partner]

object Partner extends MongoObjectMapper[Partner] {

  def asDBObject(partner: Partner): DBObject = {
    MongoDBObject(
      Id -> partner.id,
      Name -> partner.name,
      ExternalRef -> partner.externalRef.toString
    )
  }

  def fromDBObject(dbo: DBObject): Partner = {
    Partner(
      name = dbo.as[String](Name),
      externalRef = PartnerExternalRef.withName(dbo.as[String](ExternalRef)),
      id = dbo.as[ObjectId](Id)
    )
  }

}
