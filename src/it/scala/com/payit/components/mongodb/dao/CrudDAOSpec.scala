package com.payit.components.mongodb.dao

import com.mongodb.casbah.Imports._
import org.specs2.mutable.Specification

class CrudDAOSpec extends Specification {

  sequential

  val conn = MongoConnection("localhost", 27017)
  val db: MongoDB = conn("payit-specs")
  val modelCollection: MongoCollection = db("spec_models")
  modelCollection.createIndex(MongoDBObject("name" -> 1), MongoDBObject("unique" -> true))

  "testing" >> {
    success
  }

}
