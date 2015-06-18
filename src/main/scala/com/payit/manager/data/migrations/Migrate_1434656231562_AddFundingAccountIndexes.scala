package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.FundingAccountMapper

class Migrate_1434656231562_AddFundingAccountIndexes extends MongoMigration {

  def up(db: MongoDB) = {
    db(MongoCollections.FundingAccounts.toString).createIndex(
      MongoDBObject(FundingAccountMapper.ExternalRef -> 1),
      MongoDBObject("unique" -> true, "name" -> "UNIQ_EXTERNAL_REF_IDX")
    )
  }

  def down(db: MongoDB) = {
    db(MongoCollections.FundingAccounts.toString).dropIndex("UNIQ_EXTERNAL_REF_IDX")
  }

}
