fork := true

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.ambantis",
      scalaVersion := Version.scala,
      scalacOptions ++= List("-Xlint", "-Yrangepos"),
      addCompilerPlugin("org.scalameta" % "semanticdb-scalac" % Version.semanticDb cross CrossVersion.full)
    )
  ),
  Global / semanticdbEnabled := true,
  name := "auth-service",
  libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % Version.logback,
    "com.typesafe.scala-logging" %% "scala-logging" % Version.scalaLogging,
    "io.circe" %% "circe-core" % Version.circe,
    "io.circe" %% "circe-generic" % Version.circe,
    "io.circe" %% "circe-parser" % Version.circe,
    "org.apache.pekko" %% "pekko-actor-testkit-typed" % Version.pekko % Test,
    "org.apache.pekko" %% "pekko-actor-typed" % Version.pekko,
    "org.apache.pekko" %% "pekko-http-spray-json" % Version.pekkoHttp,
    "org.apache.pekko" %% "pekko-http-testkit" % Version.pekkoHttp % Test,
    "org.apache.pekko" %% "pekko-http" % Version.pekkoHttp,
    "org.apache.pekko" %% "pekko-stream" % Version.pekko,
    "org.scalatest" %% "scalatest" % Version.scalaTest % Test
  )
)
