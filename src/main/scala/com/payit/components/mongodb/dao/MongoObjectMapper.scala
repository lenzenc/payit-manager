package com.payit.components.mongodb.dao

import com.mongodb.DBObject
import com.payit.components.mongodb.models.MongoModel

trait MongoObjectMapper[M] {

  val Id = "_id"
  val Name = "name"
  val ExternalRef = "externalRef"

  com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers()

  def asDBObject(model: M): DBObject

  def fromDBObject(dbo: DBObject): M

}
