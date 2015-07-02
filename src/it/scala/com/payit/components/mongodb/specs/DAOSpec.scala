package com.payit.components.mongodb.specs

import com.mongodb.casbah.Imports._
import com.payit.components.core.Configuration
import com.payit.components.mongodb.migrations.{ResetApplyMigrations, MongoMigrator}
import com.payit.manager.data.MongoCollections
import org.specs2.execute.{Result, AsResult}
import org.specs2.mutable.{Before, Specification}
import org.specs2.specification.AroundEach

trait DAOSpec extends Specification with Before with AroundEach {

  sequential

  lazy val config = Configuration.load

  lazy val client: MongoClient = MongoClient(
    config.getString(s"mongo.test.host").getOrElse("localhost"),
    config.getInt(s"mongo.test.port").getOrElse(27017))
  val dbName: String = config.getString(s"mongo.test.dbname").getOrElse(
    sys.error(s"Unable to find property: 'mongo.test.dbname'")
  )
  implicit lazy val db = client("dbName")

  def before = {
    new MongoMigrator("test", config).migrate(ResetApplyMigrations, "com.payit.manager.data.migrations")
  }

  def around[T : AsResult](t: => T): Result = {
    try { AsResult(t) } finally { MongoCollections.values.foreach { cl => db(cl.toString).remove(MongoDBObject.empty) } }
  }

}
