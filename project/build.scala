import sbt._
import sbt.Keys._
import spray.revolver.RevolverPlugin._

object PaymentManager extends Build {

  lazy val time = inputKey[Unit]("Prints a Migration Script Name with a Current Timestamp")
  val testAll = TaskKey[Unit]("test-all", "Runs All Unit & Integration Tests")

  val testAllTask = testAll := ()

//  testAll <<= testAll.dependsOn(test in Test)

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
    parallelExecution in IntegrationTest := false
  )

  lazy val paymentManager = Project(id = "payit-manager", base = file("."),
    configurations = Seq(
      IntegrationTest
    ),
    settings = commonSettings ++ Seq(
      name := "payit-manager",
      libraryDependencies ++= Seq(
        "org.specs2" %% "specs2-core" % "3.6" % "compile",
        "io.spray" %% "spray-can" % "1.3.3" % "compile",
        "io.spray" %% "spray-routing" % "1.3.3" % "compile",
        "io.spray" %% "spray-testkit" % "1.3.3" % "compile",
        "io.spray"%%"spray-json"%"1.3.2" % "compile",
        "joda-time" % "joda-time" % "2.6" % "compile",
        "org.joda" % "joda-convert" % "1.7" % "compile",
        "org.mongodb" % "casbah_2.11" % "2.8.1" % "compile",
        "com.typesafe" % "config" % "1.3.0" % "compile",
        "org.clapper" % "classutil_2.11" % "1.0.5" % "compile",
        "com.novus" % "salat_2.11" % "1.9.9" % "compile"
      ),
      time := {
        println(s"Migrate_${System.currentTimeMillis()}_")
      },
      testAllTask
    ) ++ Revolver.settings ++ Defaults.itSettings ++ Seq(
      testAll <<= testAll.dependsOn(test in IntegrationTest),
      testAll <<= testAll.dependsOn(test in Test)
    )
  )

}