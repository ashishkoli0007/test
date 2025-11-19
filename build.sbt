name := "models"
ThisBuild / organization := "tech.minna"

ThisBuild / scalaVersion := "2.13.17"
ThisBuild / crossScalaVersions := Seq("2.13.17")

scalacOptions := tech.minna.sbt.plugin.fresh.ScalaCompilerFlags.allBestPractices(VersionNumber(scalaVersion.value))

// Enabling compatibility check with new Scala 3 compiler
scalacOptions += "-Xsource:3"

enablePlugins(MinnaLibraryPlugin, GitVersioningPlugin)

val cogsVersion = "28.4.0"

libraryDependencies ++= Seq(
  "tech.minna" %% "apigen-core" % "20.6.0",
  "tech.minna" %% "cogs" % cogsVersion,
  "tech.minna" %% "cogs-testkit" % cogsVersion % Test,
  "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  "org.scalamock" %% "scalamock" % "7.5.0" % Test,
  "org.scalatestplus" %% "scalacheck-1-15" % "3.2.11.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.19.0" % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.6.20" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.10" % Test,

  ("com.github.etaty" % "rediscala_2.12" % "1.9.0")
    .exclude("com.typesafe.akka", "akka-actor")
    .exclude("com.typesafe.akka", "akka-stream")
    .exclude("com.typesafe.akka", "akka-slf4j")
)



// coursier has issues with s3 buckets, sometimes it throws checksum error exceptions
// revert back to ivy before this issue is fixed.
useCoursier := false

Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oDG")

ThisBuild / githubOwner := "minna-technologies"
ThisBuild / githubRepository := "models"
ThisBuild / publishMavenStyle := true
