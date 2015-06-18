package com.payit.components.mongodb.dao

import com.mongodb.casbah.Imports.MongoClient
import com.payit.components.mongodb.models.MongoModel

trait MongoDAO[M <: MongoModel[M]] {

  protected def client: MongoClient

}
