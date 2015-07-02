package com.payit.manager.data.mappers

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.mongodb.dao.MongoObjectMapper
import com.payit.manager.models.Application

class ApplicationMapper extends MongoObjectMapper[Application] {

  import ApplicationMapper._

  def asDBObject(application: Application): DBObject = {
    MongoDBObject(
      Id -> application.id,
      Name -> application.name,
      ApiKey -> application.apiKey,
      Timestamps -> timestamps(application)
    )
  }

  def fromDBObject(dbo: DBObject): Application = {
    Application(
      id = dbo.as[ObjectId](Id),
      name = dbo.as[String](Name),
      apiKey = dbo.as[String](ApiKey),
      timestamps = timestamps(dbo)
    )
  }

}

object ApplicationMapper extends CommonFields {

  val ApiKey = "apiKey"

}
