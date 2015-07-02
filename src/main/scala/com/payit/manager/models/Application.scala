package com.payit.manager.models

import com.mongodb.casbah.Imports._
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.models.MongoModel
import org.joda.time.DateTime

case class Application(
                        name: String,
                        apiKey: String,
                        timestamps: Timestamps = Timestamps(),
                        id: ObjectId = new ObjectId)
extends MongoModel[Application]
{

  def withUpdatedAt(updatedAt: DateTime): Application = copy(timestamps = timestamps.updated(updatedAt))

}
