package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.daos.PartnerDAO
import com.payit.manager.models.Partner

class Migrate_1434591387634_AddInitialPartners extends MongoMigration {

  val partnerA = Partner("Partner A", "PARTNERA")
  val partnerB = Partner("Partner B", "PARTNERB")

  def up(db: MongoDB) = {

    val partnerDAO = new PartnerDAO()(db)

    partnerDAO.insert(partnerA)
    partnerDAO.insert(partnerB)

  }

  def down(db: MongoDB) = {
    db(MongoCollections.Partners.toString).remove(MongoDBObject(Partner.Id -> partnerA.id))
    db(MongoCollections.Partners.toString).remove(MongoDBObject(Partner.Id -> partnerB.id))
  }

}
