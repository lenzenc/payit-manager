package com.payit.components.mongodb.migrations

import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoCollection, MongoDB}

trait MongoMigration {

  def up(db: MongoDB)

  def down(db: MongoDB)

  def addUniqueIndex(collection: MongoCollection, field: String, name: String) = {
    collection.createIndex(
      MongoDBObject(field -> 1),
      MongoDBObject("unique" -> true, "name" -> name)
    )
  }

  def dropIndex(collection: MongoCollection, name: String) = {
    collection.dropIndex(name)
  }

}
