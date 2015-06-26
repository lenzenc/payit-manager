package com.payit.manager.data.mappers

import com.mongodb.DBObject
import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.Partner
import org.joda.time.DateTime

class PartnerMapper extends MongoObjectMapper[Partner] {

  import PartnerMapper._

  def asDBObject(partner: Partner): DBObject = {
    MongoDBObject(
      Id -> partner.id,
      Timestamps -> MongoDBObject(
        CreatedAt -> partner.timestamps.createdAt,
        UpdatedAt -> partner.timestamps.updatedAt
      ),
      Name -> partner.name,
      ExternalRef -> partner.externalRef.toString
    )
  }

  def fromDBObject(dbo: DBObject): Partner = {
    Partner(
      name = dbo.as[String](Name),
      externalRef = Partner.ExternalRef.withName(dbo.as[String](ExternalRef)),
      timestamps = com.payit.components.core.models.Timestamps(
        createdAt = dbo.as[DBObject](Timestamps).as[DateTime](CreatedAt),
        updatedAt = dbo.as[DBObject](Timestamps).as[DateTime](UpdatedAt)
      ),
      id = dbo.as[ObjectId](Id)
    )
  }

}

object PartnerMapper extends CommonFields
