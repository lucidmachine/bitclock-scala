ThisBuild / version      := "0.1.0"
ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.lucidmachinery"

lazy val core = (project in file("."))
  .settings(
    name := "BitClock",
    libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.22.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  )

