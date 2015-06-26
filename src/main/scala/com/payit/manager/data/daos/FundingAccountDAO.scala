package com.payit.manager.data.daos

import com.mongodb.casbah.{MongoCollection, MongoDB}
import com.payit.components.mongodb.dao.CrudDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.FundingAccountMapper
import com.payit.manager.models.FundingAccount

class FundingAccountDAO(implicit val db: MongoDB) extends CrudDAO[FundingAccount] {

  protected lazy val collection: MongoCollection = db(MongoCollections.FundingAccounts.toString)
  protected val mapper = new FundingAccountMapper

}
