package com.payit.manager

import com.mongodb.casbah.Imports._
import com.payit.components.core.Configuration

trait MongoDBSupport {

  def config: Configuration

  val client: MongoClient = MongoClient(
    config.getString("mongo.default.host").getOrElse("localhost"),
    config.getInt("mongo.default.port").getOrElse(27017))
  implicit val db: MongoDB = client(config.getString("mongo.default.dbname").get)

}
