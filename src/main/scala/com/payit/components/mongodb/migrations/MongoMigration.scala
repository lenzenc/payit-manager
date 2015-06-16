package com.payit.components.mongodb.migrations

import com.mongodb.casbah.MongoDB

trait MongoMigration {

  def up(db: MongoDB)

  def down(db: MongoDB)

}
