organization := "com.github.ktakagaki.breeze"

name := "breeze-parent"

lazy val root = project.in( file(".") )
    .aggregate(math, natives, viz, macros).dependsOn(math, viz)

lazy val macros = project.in( file("macros"))

lazy val math = project.in( file("math")).dependsOn(macros)

lazy val natives = project.in(file("natives")).dependsOn(math)

lazy val viz = project.in( file("viz")).dependsOn(math)

lazy val benchmark = project.in(file("benchmark")).dependsOn(math, natives)

scalaVersion := Common.scalaVersion

crossScalaVersions  := Common.crossScalaVersions

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.0.1" cross CrossVersion.full)

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/ktakagaki/nounou</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:ktakagaki/breeze.git</url>
      <connection>scm:git:git@github.com:ktakagaki/breeze.git</connection>
    </scm>
    <developers>
      <developer>
        <id>ktakagaki</id>
        <name>Kenta Takagaki</name>
        <url>http://www.kentarohtakagaki.org/</url>
      </developer>
    </developers>)
