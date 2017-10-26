name := "soyosoyo"

Common.commonSettings

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)

resolvers ++= Seq(
  Resolver.typesafeRepo("releases")
)

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
)

libraryDependencies := {
  val sv = scalaVersion.value
  val deps = libraryDependencies.value
  sv match {
    case x if x startsWith "2.10" =>
      deps :+ compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
    case _ =>
      deps
  }
}

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 12 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.6" % "test"
      )
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.6" % "test"
      )
    case _ =>
      libraryDependencies.value ++ Seq()
  }
}

//// see https://github.com/typesafehub/scalalogging/issues/23
//testOptions in Test += Tests.Setup(classLoader =>
//  try {
//    classLoader
//      .loadClass("org.slf4j.LoggerFactory")
//      .getMethod("getLogger", classLoader.loadClass("java.lang.String"))
//      .invoke(null, "ROOT")
//  } catch {
//    case _: Exception =>
//  }
//)

fork in Compile := true

javaOptions := Seq("-Xmx4g")

