package com.payit.manager.models

import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.models.MongoModel
import com.mongodb.casbah.Imports._
import org.joda.time.DateTime


case class Partner(
                    name: String,
                    timestamps: Timestamps = Timestamps(),
                    id: ObjectId = new ObjectId)
  extends MongoModel[Partner]
{
  def withUpdatedAt(updatedAt: DateTime): Partner = copy(timestamps = timestamps.updated(updatedAt))
}
