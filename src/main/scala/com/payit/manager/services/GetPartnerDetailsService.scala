package com.payit.manager.services

import com.payit.manager.data.daos.PartnerDAO
import com.payit.manager.services.dtos.PartnerDetail

class GetPartnerDetailsService(partnerDAO: PartnerDAO) {

  def list(): Seq[PartnerDetail] = partnerDAO.findAll().map { partner =>
    new PartnerDetail(partner.name, partner.externalRef)
  }

  def get(id: String): Option[PartnerDetail] = partnerDAO.findById(id).map { partner =>
    new PartnerDetail(partner.name, partner.externalRef)
  }

}
