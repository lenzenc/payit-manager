package com.payit.manager.services

import com.payit.manager.data.daos.PartnerDAO
import com.payit.manager.services.dtos.PartnerDetail
import com.mongodb.casbah.Imports.ObjectId

class GetPartnerDetailsService(partnerDAO: PartnerDAO) {

  def list(): Seq[PartnerDetail] = partnerDAO.findAll().map { partner =>
    new PartnerDetail(partner.name, partner.externalRef.toString)
  }

  def get(id: String): Option[PartnerDetail] = partnerDAO.findById(new ObjectId(id)).map { partner =>
    new PartnerDetail(partner.name, partner.externalRef.toString)
  }

}
