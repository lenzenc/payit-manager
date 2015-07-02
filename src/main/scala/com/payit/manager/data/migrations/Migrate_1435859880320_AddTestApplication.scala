package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.ApplicationMapper
import com.payit.manager.models.Application

class Migrate_1435859880320_AddTestApplication extends MongoMigration {

  val app = Application(
    "Test Application",
    new ObjectId("55956a98a92cbfba8b2d65de").toString)

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.Applications.toString)
    collection.insert(new ApplicationMapper().asDBObject(app))
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.Applications.toString)
    collection.remove(MongoDBObject(ApplicationMapper.Id -> app.id))
  }

}
