package com.payit.manager.data.migrations

import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.BankAccountMapper

class Migrate_1435864590282_BankAccountIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.BankAccounts.toString)
    addUniqueIndex(collection, "UNIQ_ACCOUNT_ID_IDX", BankAccountMapper.ApplicationId, BankAccountMapper.AccountId)
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.BankAccounts.toString)
    dropIndex(collection, "UNIQ_ACCOUNT_ID_IDX")
  }

}
