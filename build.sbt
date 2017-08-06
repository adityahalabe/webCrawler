name := """webCrawler"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.jsoup" % "jsoup" % "1.8.3",
  "com.google.code" % "sitemapgen4j" % "1.0.1"

)

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
