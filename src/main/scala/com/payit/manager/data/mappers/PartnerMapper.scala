package com.payit.manager.data.mappers

import com.mongodb.DBObject
import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.{PartnerExternalRef, Partner}

class PartnerMapper extends MongoObjectMapper[Partner] {

  import PartnerMapper._

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

object PartnerMapper extends CommonFields
