package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.PartnerMapper

class Migrate_1434587082240_AddPartnerIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.Partners.toString)
    addUniqueIndex(collection, PartnerMapper.Name, "UNIQ_NAME_IDX")
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.Partners.toString)
    dropIndex(collection, "UNIQ_NAME_IDX")
  }

}
