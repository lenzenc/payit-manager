package com.payit.manager.data.mappers

import com.payit.components.mongodb.dao.ModelMapper
import com.payit.manager.models.Partner
import com.mongodb.casbah.Imports._

object PartnerMapper extends ModelMapper[Partner] {

  def asDBObject(partner: Partner): DBObject = {
    MongoDBObject(
      Id -> partner.id,
      Name -> partner.name,
      ExternalRef -> partner.externalRef
    )
  }

  def fromDBObject(dbo: DBObject): Partner = {
    Partner(
      name = dbo.as[String](Name),
      externalRef = dbo.as[String](ExternalRef),
      id = dbo.as[ObjectId](Id)
    )
  }

}
