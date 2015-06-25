package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.{MongoCollection, MongoDB}
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.FundingAccountMapper
import com.payit.manager.models.FundingAccount

class FundingAccountDAO(implicit val db: MongoDB) extends DAO[FundingAccount] {

  protected lazy val collection: MongoCollection = db(MongoCollections.FundingAccounts.toString)
  protected val mapper = new FundingAccountMapper

  import FundingAccountMapper._

  def findById(id: String): Option[FundingAccount] = collection.findOne(MongoDBObject(Id -> new ObjectId(id))) match {
    case Some(rec) => Some(mapper.fromDBObject(rec))
    case None => None
  }

  def insert(fundingAccount: FundingAccount): FundingAccount = {
    collection.insert(mapper.asDBObject(fundingAccount), WriteConcern.Acknowledged)
    fundingAccount
  }

}
