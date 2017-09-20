//enablePlugins(GitVersioning)

Common.commonSettings

name := "breeze-parent"

lazy val root = project.in(file("."))
  .aggregate(math, natives, viz, macros, soyosoyo, graphics).dependsOn(math, viz, soyosoyo, graphics)

lazy val macros = project.in(file("macros"))

lazy val math = project.in(file("math")).dependsOn(macros)

lazy val natives = project.in(file("natives")).dependsOn(math)

lazy val viz = project.in(file("viz")).dependsOn(math)

lazy val soyosoyo = project.in(file("soyosoyo")).dependsOn(math)

lazy val graphics = project.in(file("graphics")).dependsOn(math, soyosoyo)

lazy val benchmark = project.in(file("benchmark")).dependsOn(math, natives)
addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)


