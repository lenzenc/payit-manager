package com.payit.manager.data.mappers

import com.mongodb.DBObject
import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.Partner

class PartnerMapper extends MongoObjectMapper[Partner] {

  import PartnerMapper._

  def asDBObject(partner: Partner): DBObject = {
    MongoDBObject(
      Id -> partner.id,
      Timestamps -> timestamps(partner),
      Name -> partner.name,
      ExternalRef -> partner.externalRef.toString
    )
  }

  def fromDBObject(dbo: DBObject): Partner = {
    Partner(
      name = dbo.as[String](Name),
      externalRef = Partner.ExternalRef.withName(dbo.as[String](ExternalRef)),
      timestamps = timestamps(dbo),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object PartnerMapper extends CommonFields
