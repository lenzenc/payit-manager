package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.models.Payment

class Migrate_1434675300583_AddPaymentIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    db(MongoCollections.Payments.toString).createIndex(
      MongoDBObject(Payment.ExternalRef -> 1),
      MongoDBObject("unique" -> true, "name" -> "UNIQ_EXTERNAL_REF_IDX")
    )
  }

  def down(db: MongoDB) = {
    db(MongoCollections.Payments.toString).dropIndex("UNIQ_EXTERNAL_REF_IDX")
  }

}
