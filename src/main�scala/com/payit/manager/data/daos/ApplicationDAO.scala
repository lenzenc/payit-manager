package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.{MongoCollection, MongoDB}
import com.payit.components.mongodb.dao.CrudDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.ApplicationMapper
import com.payit.manager.models.Application

class ApplicationDAO(implicit val db: MongoDB) extends CrudDAO[Application] {

  protected lazy val collection: MongoCollection = db(MongoCollections.Applications.toString)
  protected val mapper = new ApplicationMapper

  def findByApiKey(apiKey: String): Option[Application] = find(MongoDBObject(ApplicationMapper.ApiKey -> apiKey))

}
