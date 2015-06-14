import sbt._
import Keys._
import spray.revolver.RevolverPlugin._

object PaymentManager extends Build {

  lazy val _scalacOptions = Seq("-deprecation", "-unchecked", "-feature")

  lazy val commonSettings = Seq(
    version := "1.0",
    organization := "com.payit",
    scalaVersion := "2.11.4",
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
    resolvers ++= Seq(
      "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases",
      "Sonatype Releases"  at "http://oss.sonatype.org/content/repositories/releases",
      "spray repo" at "http://repo.spray.io/",
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
    ),
    scalacOptions ++= _scalacOptions,
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature"),
    scalacOptions in Test ++= Seq("-Yrangepos"),
    parallelExecution in Test := false
  )

  lazy val paymentManager = Project(id = "payit-manager", base = file("."),
    settings = commonSettings ++ Seq(
      name := "payit-manager",
      libraryDependencies ++= Seq(
        "org.specs2" %% "specs2-core" % "3.6" % "test",
        "io.spray" %% "spray-can" % "1.3.3" % "compile",
        "io.spray" %% "spray-routing" % "1.3.3" % "compile",
        "io.spray" %% "spray-testkit" % "1.3.3" % "compile",
        "io.spray"%%"spray-json"%"1.3.2" % "compile"
      )
    ) ++ Revolver.settings
  )

}