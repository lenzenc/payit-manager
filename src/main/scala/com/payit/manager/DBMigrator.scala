package com.payit.manager

import com.payit.components.core.Configuration
import com.payit.components.mongodb.migrations.{MongoMigrator, MigratorCommand}

trait DBMigrator {

  def config: Configuration

  val basePackage = "com.payit.manager.data.migrations"
  val mongoConfig = "default"
  lazy val migrator = new MongoMigrator("default", config)

  def migrateDB(command: MigratorCommand) = {
    migrator.migrate(command, basePackage)
  }

}
