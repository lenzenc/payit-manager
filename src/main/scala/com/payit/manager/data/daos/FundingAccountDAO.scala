package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.{MongoCollection, MongoDB}
import com.payit.components.mongodb.dao.MongoDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.FundingAccountMapper
import com.payit.manager.models.FundingAccount

class FundingAccountDAO(implicit val db: MongoDB) extends MongoDAO[FundingAccount] {

  protected lazy val collection: MongoCollection = db(MongoCollections.FundingAccounts.toString)

  def insert(fundingAccount: FundingAccount): FundingAccount = {
    collection.insert(FundingAccountMapper.asDBObject(fundingAccount), WriteConcern.Acknowledged)
    fundingAccount
  }

}
