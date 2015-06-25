package com.payit.manager.data.migrations

import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.PaymentMapper

class Migrate_1434675300583_AddPaymentIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.Payments.toString)
    addUniqueIndex(collection, PaymentMapper.ExternalRef, "UNIQ_EXTERNAL_REF_IDX")
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.Payments.toString)
    dropIndex(collection, "UNIQ_EXTERNAL_REF_IDX")
  }

}
