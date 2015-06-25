package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoCollection, MongoDB}
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.BankAccountMapper
import com.payit.manager.models.BankAccount

class BankAccountDAO(implicit val db: MongoDB) extends DAO[BankAccount] {

  protected lazy val collection: MongoCollection = db(MongoCollections.BankAccounts.toString)
  protected val mapper = new BankAccountMapper

  import BankAccountMapper._

  def findById(id: String): Option[BankAccount] = {
    collection.findOne(MongoDBObject(Id -> new ObjectId(id))) match {
      case Some(rec) => Some(mapper.fromDBObject(rec))
      case None => None
    }
  }

}
