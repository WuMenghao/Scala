import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.11.8"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

lazy val sparkbuild = (project in file("."))
  .aggregate(sparkdemo).aggregate(scalademo)
  .dependsOn(sparkdemo).dependsOn(scalademo)
  .enablePlugins()
  .enablePlugins()
  .settings(
    name := "sparkbuild"

  )

lazy val scalademo = (project in file("scalademo"))
  .settings(
    name := "scalademo"
  )

lazy val sparkdemo = (project in file("sparkdemo"))
  .settings(
    name := "sparkdemo",
    libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.3.2" % "provided",
    libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.3.2" % "provided",
    libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.3.2" % "provided",
    libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.11" % "1.6.3", //注意版本
    libraryDependencies += scalaTest % Test
  )
