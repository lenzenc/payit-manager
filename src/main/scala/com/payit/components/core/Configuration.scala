package com.payit.components.core

import com.typesafe.config.{ConfigFactory, ConfigException, Config}

import scala.collection.JavaConverters._
import scala.util.control.NonFatal

case class Configuration(underlying: Config) {

  import Configuration.asScalaList

  def getConfig(path: String): Option[Configuration] = getValue(path, underlying.getConfig(path)).map(Configuration(_))

  def getString(path: String): Option[String] = getValue(path, underlying.getString(path))

  def getBoolean(path: String): Option[Boolean] = getValue(path, underlying.getBoolean(path))

  def getInt(path: String): Option[Int] = getValue(path, underlying.getInt(path))

  def getLong(path: String): Option[Long] = getValue(path, underlying.getLong(path))

  def getMilliseconds(path: String): Option[Long] = getLong(path)

  def subKeys: Set[String] = underlying.root().keySet().asScala.toSet

  private def getValue[T](path: String, v: => T): Option[T] = {
    try {
      Option(v)
    } catch {
      case e: ConfigException.Missing => None
      case NonFatal(e) => throw e
    }
  }

}

object Configuration {

  private lazy val config: Config = ConfigFactory.load

  def load: Configuration = Configuration(config)

  private[Configuration] def asScalaList[A](l: java.util.List[A]): Seq[A] = asScalaBufferConverter(l).asScala.toList

  def empty = Configuration(ConfigFactory.empty())

}
