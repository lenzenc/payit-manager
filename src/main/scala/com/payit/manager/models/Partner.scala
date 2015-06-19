package com.payit.manager.models

import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.components.mongodb.models.MongoModel
import com.mongodb.casbah.Imports._

case class Partner(name: String, externalRef: String, id: ObjectId = new ObjectId) extends MongoModel[Partner]

object Partner extends MongoObjectMapper[Partner] {

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
