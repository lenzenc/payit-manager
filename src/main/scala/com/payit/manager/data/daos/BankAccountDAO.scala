package com.payit.manager.data.daos

import com.mongodb.casbah.{MongoCollection, MongoDB}
import com.payit.components.mongodb.dao.CrudDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.BankAccountMapper
import com.payit.manager.models.BankAccount

class BankAccountDAO(implicit val db: MongoDB) extends CrudDAO[BankAccount] {

  protected lazy val collection: MongoCollection = db(MongoCollections.BankAccounts.toString)
  protected val mapper = new BankAccountMapper

}
