package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.models.Partner

class Migrate_1434587082240_AddPartnerIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.Partners.toString)
    addUniqueIndex(collection, Partner.Name, "UNIQ_NAME_IDX")
    addUniqueIndex(collection, Partner.ExternalRef, "UNIQ_EXTERNAL_REF_IDX")
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.Partners.toString)
    dropIndex(collection, "UNIQ_NAME_IDX")
    dropIndex(collection, "UNIQ_EXTERNAL_REF_IDX")
  }

}
