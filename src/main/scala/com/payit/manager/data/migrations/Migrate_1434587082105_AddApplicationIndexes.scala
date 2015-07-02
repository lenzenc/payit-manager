package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.ApplicationMapper

class Migrate_1434587082105_AddApplicationIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.Applications.toString)
    addUniqueIndex(collection, "UNIQ_NAME_IDX", ApplicationMapper.Name)
    addUniqueIndex(collection, "UNIQ_APIKEY_IDX", ApplicationMapper.ApiKey)
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.Applications.toString)
    dropIndex(collection, "UNIQ_NAME_IDX")
    dropIndex(collection, "UNIQ_APIKEY_IDX")
  }

}
