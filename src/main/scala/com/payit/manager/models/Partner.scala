package com.payit.manager.models

import com.payit.components.mongodb.models.MongoModel
import com.mongodb.casbah.Imports.ObjectId

case class Partner(name: String, externalRef: String, id: ObjectId = new ObjectId) extends MongoModel[Partner]
