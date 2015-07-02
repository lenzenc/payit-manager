package com.payit.components.mongodb.migrations

import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoCollection, MongoDB}

trait MongoMigration {

  def up(db: MongoDB)

  def down(db: MongoDB)

  def addUniqueIndex(collection: MongoCollection, name: String, fields: String*) = {
    var map = MongoDBObject()
    fields.foreach { i => map.put(i, 1) }
    collection.createIndex(
      map,
      MongoDBObject("unique" -> true, "name" -> name)
    )
  }

  def dropIndex(collection: MongoCollection, name: String) = {
    collection.dropIndex(name)
  }

}
