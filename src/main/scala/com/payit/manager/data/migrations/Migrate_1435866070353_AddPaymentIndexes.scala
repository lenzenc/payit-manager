package com.payit.manager.data.migrations

import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.PaymentMapper

class Migrate_1435866070353_AddPaymentIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.Payments.toString)
    addUniqueIndex(collection, "UNIQ_PAYMENT_ID_IDX", PaymentMapper.ApplicationId, PaymentMapper.PaymentId)
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.Payments.toString)
    dropIndex(collection, "UNIQ_PAYMENT_ID_IDX")
  }

}
