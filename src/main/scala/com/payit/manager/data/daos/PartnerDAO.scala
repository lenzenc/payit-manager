package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.{MongoDB, MongoCollection}
import com.payit.components.mongodb.dao.MongoDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.PartnerMapper
import com.payit.manager.models.Partner

class PartnerDAO(implicit val db: MongoDB) extends MongoDAO[Partner] {

  protected lazy val collection: MongoCollection = db(MongoCollections.Partners.toString)

  def insert(partner: Partner): Partner = {
    collection.insert(PartnerMapper.asDBObject(partner), WriteConcern.Acknowledged)
    partner
  }

}
