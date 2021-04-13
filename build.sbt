name := "testcontainer-scala"

version := "0.1"

scalaVersion := "2.13.5"

val testcontainersScalaVersion = "0.39.3"

libraryDependencies ++= Seq(
  "com.dimafeng" %% "testcontainers-scala-scalatest" % testcontainersScalaVersion % "test",
  "com.dimafeng" %% "testcontainers-scala-mssqlserver" % testcontainersScalaVersion % "test",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "8.4.1.jre8" % "test",
  "com.zaxxer" % "HikariCP" % "4.0.3" % "test",
  "org.scalatest" %% "scalatest" % "3.2.7" % "test"
)

Test / fork := true
