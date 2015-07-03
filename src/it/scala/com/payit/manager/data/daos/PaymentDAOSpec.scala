package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.specs.DAOSpec
import com.payit.components.specs.SpecScope
import com.payit.manager.models.Payment

class PaymentDAOSpec extends DAOSpec {

  trait TestScope extends SpecScope {

    val paymentDAO = new PaymentDAO()
    val id = new ObjectId
    val applicationId = new ObjectId()
    val paymentId = new ObjectId().toString
    paymentDAO.insert(Payment(
      new ObjectId,
      new ObjectId,
      BigDecimal(100.00),
      paymentId,
      applicationId,
      Timestamps(),
      id
    ))

  }

  ".findByPaymentId" >> {
    "when a record exists for a given payment id" >> {
      "it should return expected Payment" in new TestScope {
        paymentDAO.findByPaymentId(applicationId, paymentId) must beSome.like { case p => p.id must_== id }
      }
    }
    "when no record exists for a given payment id" >> {
      "it should return None" in new TestScope {
        paymentDAO.findByPaymentId(applicationId, "doesnotexist") must beNone
      }
    }
  }

}
