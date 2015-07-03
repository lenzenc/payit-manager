package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.dao.CrudDAO
import com.payit.manager.data.MongoCollections
import com.payit.manager.data.mappers.PaymentMapper
import com.payit.manager.models.Payment

class PaymentDAO(implicit val db: MongoDB) extends CrudDAO[Payment] {

  val collection = db(MongoCollections.Payments.toString)
  val mapper = new PaymentMapper

  def findByPaymentId(applicationId: ObjectId, paymentId: String): Option[Payment] = find(MongoDBObject(
    PaymentMapper.ApplicationId -> applicationId,
    PaymentMapper.PaymentId -> paymentId
  ))

}
