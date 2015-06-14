package com.payit.manager

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp

object Server extends App with SimpleRoutingApp {

  println("Running PayIT Manager...")

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
