package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.{MongoDB, MongoCollection}
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.PartnerMapper
import com.payit.manager.models.Partner

class PartnerDAO(implicit val db: MongoDB) extends DAO[Partner] {

  protected lazy val collection: MongoCollection = db(MongoCollections.Partners.toString)
  protected val mapper = new PartnerMapper

  import PartnerMapper._

  def insert(partner: Partner): Partner = {
    collection.insert(mapper.asDBObject(partner), WriteConcern.Acknowledged)
    partner
  }

  def findAll(): Seq[Partner] = collection.find().toSeq.map(mapper.fromDBObject(_))

  def findById(id: String): Option[Partner] = collection.findOne(MongoDBObject(Id -> new ObjectId(id))) match {
    case Some(rec) => Some(mapper.fromDBObject(rec))
    case None => None
  }

}
