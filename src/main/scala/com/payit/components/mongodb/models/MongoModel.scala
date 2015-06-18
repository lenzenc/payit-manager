package com.payit.components.mongodb.models

import com.mongodb.casbah.Imports._

trait MongoModel[M] {

  def id: ObjectId

}
