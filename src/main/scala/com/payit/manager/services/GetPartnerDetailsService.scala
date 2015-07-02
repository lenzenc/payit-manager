package com.payit.manager.services

import com.payit.manager.data.daos.PartnerDAO
import com.payit.manager.services.dtos.PartnerDetails
import com.mongodb.casbah.Imports.ObjectId

class GetPartnerDetailsService(partnerDAO: PartnerDAO) {

  def list(): Seq[PartnerDetails] = partnerDAO.findAll().map { partner =>
    new PartnerDetails(partner.name, partner.externalRef.toString)
  }

  def get(id: String): Option[PartnerDetails] = partnerDAO.findById(new ObjectId(id)).map { partner =>
    new PartnerDetails(partner.name, partner.externalRef.toString)
  }

}
