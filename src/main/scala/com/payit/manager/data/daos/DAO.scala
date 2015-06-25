package com.payit.manager.data.daos

import com.payit.components.mongodb.dao.MongoDAO
import com.payit.components.mongodb.models.MongoModel

trait DAO[M <: MongoModel[M]] extends MongoDAO[M] {

}
