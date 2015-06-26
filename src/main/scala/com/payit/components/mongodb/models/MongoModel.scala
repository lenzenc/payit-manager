package com.payit.components.mongodb.models

import com.mongodb.casbah.Imports._
import com.payit.components.core.models.Timestamps
import org.joda.time.DateTime

trait MongoModel[M] {

  def id: ObjectId
  def timestamps: Timestamps

  def withUpdatedAt(updatedAt: DateTime): M

}
