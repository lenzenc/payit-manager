package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.models.Partner

class Migrate_1434587082240_AddPartnerIndexes extends MongoMigration {

  def up(db: MongoDB) = {

    db(MongoCollections.Partners.toString).createIndex(
      MongoDBObject(Partner.Name -> 1),
      MongoDBObject("unique" -> true, "name" -> "UNIQ_NAME_IDX")
    )
    db(MongoCollections.Partners.toString).createIndex(
      MongoDBObject(Partner.ExternalRef -> 1),
      MongoDBObject("unique" -> true, "name" -> "UNIQ_EXTERNAL_REF_IDX")
    )

  }

  def down(db: MongoDB) = {
    db(MongoCollections.Partners.toString).dropIndex("UNIQ_NAME_IDX")
    db(MongoCollections.Partners.toString).dropIndex("UNIQ_EXTERNAL_REF_IDX")
  }

}
