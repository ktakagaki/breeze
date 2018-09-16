//enablePlugins(GitVersioning)

Common.commonSettings

name := "breeze-parent"

  .aggregate(math, natives, viz, macros, soyosoyo).dependsOn(math, viz)
publishTo := Some({
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Opts.resolver.sonatypeSnapshots
  //Some("snapshots" at nexus + "content/repositories/snapshots")
  else Opts.resolver.sonatypeStaging
  //Some("releases"  at nexus + "service/local/staging/deploy/maven2")
})

publishArtifact in Test := false


lazy val macros = project.in(file("macros"))

lazy val math = project.in(file("math")).dependsOn(macros)

lazy val natives = project.in(file("natives")).dependsOn(math)

lazy val viz = project.in(file("viz")).dependsOn(math)

lazy val soyosoyo = project.in(file("soyosoyo")).dependsOn(math)

lazy val benchmark = project.in(file("benchmark")).dependsOn(math, natives)
