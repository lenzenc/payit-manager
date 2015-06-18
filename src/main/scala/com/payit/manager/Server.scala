package com.payit.manager

import akka.actor.ActorSystem
import com.payit.components.core.Configuration
import com.payit.components.mongodb.migrations.{ResetApplyMigrations, MongoMigrator}
import spray.routing.SimpleRoutingApp

object Server extends App with SimpleRoutingApp {

  println("Running PayIT Manager...")

  val config = Configuration.load
  val migrator = new MongoMigrator("default", config)
  migrator.migrate(ResetApplyMigrations, "com.payit.manager.data.migrations")

  implicit val system = ActorSystem("payit-manager")

  startServer(interface = "localhost", port = 9000) {
    path("") {
      get {
        complete {
          <h1>Welcome to PayIT Manager</h1>
        }
      }
    }
  }

}
