package com.payit.manager

import com.payit.manager.services.dtos.PartnerDetail
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonImplicits extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val partnerDetail = jsonFormat2(PartnerDetail)

}
