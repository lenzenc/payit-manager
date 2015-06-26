package com.payit.manager.data.daos

import com.mongodb.casbah.{MongoDB, MongoCollection}
import com.payit.components.mongodb.dao.CrudDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.PartnerMapper
import com.payit.manager.models.Partner

class PartnerDAO(implicit val db: MongoDB) extends CrudDAO[Partner] {

  protected lazy val collection: MongoCollection = db(MongoCollections.Partners.toString)
  protected val mapper = new PartnerMapper

  def findAll(): Seq[Partner] = collection.find().toSeq.map(mapper.fromDBObject(_))

}
