package com.payit.manager.data.migrations

import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.FundingAccountMapper

class Migrate_1434656231562_AddFundingAccountIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    val collection = db(MongoCollections.FundingAccounts.toString)
    addUniqueIndex(collection, FundingAccountMapper.ExternalRef, "UNIQ_EXTERNAL_REF_IDX")
  }

  def down(db: MongoDB) = {
    val collection = db(MongoCollections.FundingAccounts.toString)
    dropIndex(collection, "UNIQ_EXTERNAL_REF_IDX")
  }

}
