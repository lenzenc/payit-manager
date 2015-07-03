package com.payit.manager.data.migrations

import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.FundingAccountMapper

class Migrate_1435929818223_AddFundingAccountIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.FundingAccounts.toString)
    addUniqueIndex(collection, "UNIQ_NAME", FundingAccountMapper.ApplicationId, FundingAccountMapper.Name)
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.FundingAccounts.toString)
    collection.dropIndex("UNIQ_NAME")
  }

}
