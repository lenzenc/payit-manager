package com.payit.manager.data.daos

import com.mongodb.casbah.Imports._
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.specs.DAOSpec
import com.payit.components.specs.SpecScope
import com.payit.manager.models.Application

class ApplicationDAOSpec extends DAOSpec {

  trait TestScope extends SpecScope {

    val applicationDAO: ApplicationDAO = new ApplicationDAO()
    val apiKey = new ObjectId().toString
    val id = new ObjectId()
    applicationDAO.insert(Application("Test", apiKey, Timestamps(), id))

  }

  ".findByApiKey" >> {
    "when record exists for given apiKey" >> {
      "it should return expected application" in new TestScope {
        applicationDAO.findByApiKey(apiKey) must beSome.like { case app => app.id must_== id }
      }
    }
    "when no record exists for a given apiKey" >> {
      "it should return None" in new TestScope {
        applicationDAO.findByApiKey("doesnotexistsapikey") must beNone
      }
    }
  }

}
