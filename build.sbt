val scala3Version = "3.7.1"
import Dependencies.{testDep, *}
import sbt.Keys.libraryDependencies

lazy val root = project
  .in(file("."))
  .settings(
    name := "FootballRadarExercise",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= testDep ++ dependencies
  )
