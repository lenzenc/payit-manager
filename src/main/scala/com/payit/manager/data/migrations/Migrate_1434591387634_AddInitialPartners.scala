package com.payit.manager.data.migrations

import com.mongodb.casbah.Imports._
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.migrations.MongoMigration
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.daos.PartnerDAO
import com.payit.manager.data.mappers.PartnerMapper
import com.payit.manager.models.Partner

class Migrate_1434591387634_AddInitialPartners extends MongoMigration {

  val partnerA = Partner("Partner A", Timestamps(), new ObjectId("5595397ca92cfa400dac87af"))
  val partnerB = Partner("Partner B", Timestamps(), new ObjectId("55953ccca92c20486de4869f"))

  def up(db: MongoDB) = {

    val partnerDAO = new PartnerDAO()(db)

    partnerDAO.insert(partnerA)
    partnerDAO.insert(partnerB)

  }

  def down(db: MongoDB) = {
    db(MongoCollections.Partners.toString).remove(MongoDBObject(PartnerMapper.Id -> partnerA.id))
    db(MongoCollections.Partners.toString).remove(MongoDBObject(PartnerMapper.Id -> partnerB.id))
  }

}
