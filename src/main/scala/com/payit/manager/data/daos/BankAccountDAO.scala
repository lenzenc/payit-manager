package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoCollection, MongoDB}
import com.payit.components.mongodb.dao.MongoDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.models.BankAccount

class BankAccountDAO(implicit val db: MongoDB) extends MongoDAO[BankAccount] {

  protected lazy val collection: MongoCollection = db(MongoCollections.BankAccounts.toString)

  def findById(id: String): Option[BankAccount] = {
    collection.findOne(MongoDBObject(BankAccount.Id -> new ObjectId(id))) match {
      case Some(rec) => Some(BankAccount.fromDBObject(rec))
      case None => None
    }
  }

}
