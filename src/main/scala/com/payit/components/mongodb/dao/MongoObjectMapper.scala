package com.payit.components.mongodb.dao

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.models.MongoModel
import org.joda.time.DateTime
import com.mongodb.casbah.Imports._

trait MongoObjectMapper[M <: MongoModel[M]] {

  com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers()

  val Timestamps = "timestamps"
  val CreatedAt = "createdAt"
  val UpdatedAt = "updatedAt"

  def asDBObject(model: M): DBObject

  def fromDBObject(dbo: DBObject): M

  protected def timestamps(model: M): DBObject = {
    MongoDBObject(
      CreatedAt -> model.timestamps.createdAt,
      UpdatedAt -> model.timestamps.updatedAt
    )
  }

  protected def timestamps(dbo: DBObject): Timestamps = {
    com.payit.components.core.models.Timestamps(
      createdAt = dbo.as[DBObject](Timestamps).as[DateTime](CreatedAt),
      updatedAt = dbo.as[DBObject](Timestamps).as[DateTime](UpdatedAt)
    )
  }

}
