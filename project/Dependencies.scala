import sbt.*

object Dependencies {


  val testDep: Seq[ModuleID] = Seq(
    "org.scalameta" %% "munit" % "1.0.0" % Test,
    "org.typelevel" %% "munit-cats-effect" % "2.1.0" % Test
  )

  val dependencies: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-core" % "2.13.0",
  "org.typelevel" %% "cats-effect" % "3.6.2"
  )

}
