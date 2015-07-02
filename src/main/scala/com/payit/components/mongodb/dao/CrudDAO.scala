package com.payit.components.mongodb.dao

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoCollection
import com.payit.components.mongodb.models.MongoModel
import org.joda.time.DateTime

trait CrudDAO[M <: MongoModel[M]] extends MongoDAO[M] {

  protected val mapper: MongoObjectMapper[M]
  protected val collection: MongoCollection

  def findById(id: ObjectId): Option[M] = Some(mapper.fromDBObject(collection.findOneByID(id).getOrElse(return None)))

  def insert(model: M): M = {
    if (model.id == null) sys.error(s"Unable to insert model: ${model.getClass.getName} because id is null")
    val updatedModel = model.withUpdatedAt(model.timestamps.createdAt)
    collection.insert(mapper.asDBObject(updatedModel), WriteConcern.Acknowledged)
    updatedModel
  }

  def update(model: M): M = {
    val updatedModel = model.withUpdatedAt(DateTime.now)
    collection.update(
      MongoDBObject("_id" -> model.id),
      mapper.asDBObject(updatedModel),
      false,
      false,
      WriteConcern.Acknowledged)
    updatedModel
  }

  def delete(id: ObjectId) = collection.remove(MongoDBObject("_id" -> id))

  def find(dbo: MongoDBObject): Option[M] = Some(mapper.fromDBObject(collection.findOne(dbo).getOrElse(return None)))

}
