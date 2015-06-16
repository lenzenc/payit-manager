package com.payit.components.mongodb.migrations

sealed trait MigrationDirection

case object Up extends MigrationDirection
case object Down extends MigrationDirection
