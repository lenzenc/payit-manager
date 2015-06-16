package com.payit.components.mongodb.migrations

sealed trait MigratorCommand

case object ApplyMigrations extends MigratorCommand
case object ResetApplyMigrations extends MigratorCommand
case class RollbackMigration(versions: Int = 1) extends MigratorCommand
