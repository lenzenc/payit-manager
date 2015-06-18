package com.payit.components.mongodb.dao

import com.mongodb.casbah.MongoDB
import com.payit.components.mongodb.models.MongoModel

trait MongoDAO[M <: MongoModel[M]] {

  protected def db: MongoDB

}
