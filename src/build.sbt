name := """linkedpipes-discovery"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
    filters,
    jdbc,
    cache,
    ws,
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "com.jsuereth" %% "scala-arm" % "1.4",
    "org.apache.jena" % "jena" % "3.1.0" exclude("org.slf4j", "slf4j-log4j12"),
    "org.apache.jena" % "jena-arq" % "3.1.0" exclude("org.slf4j", "slf4j-log4j12"),
    "com.typesafe.akka" %% "akka-actor" % "2.4.10",
    "org.apache.commons" % "commons-io" % "1.3.2",
    "ai.x" %% "play-json-extensions" % "0.8.0",
    "org.scalaj" %% "scalaj-http" % "2.3.0"
)



fork in run := false