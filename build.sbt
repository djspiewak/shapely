organization := "com.codecommit"

name := "shapely"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.9"

scalacOptions in Compile += "-language:_"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",
  "org.typelevel" %% "macro-compat" % "1.1.1",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
  compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)