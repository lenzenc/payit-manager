package com.payit.manager.models

case class Partner(name: String, externalRef: String, supportedFunding: Seq[PartnerFunding])
