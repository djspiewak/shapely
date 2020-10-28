organization := "com.codecommit"

name := "shapely"

version := "0.1-SNAPSHOT"

scalaVersion := "2.13.2"

scalacOptions in Compile += "-language:_"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",
  "com.github.dmytromitin" %% "macro-compat" % "1.1.2",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided"
)