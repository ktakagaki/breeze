scalacOptions in Global += "-deprecation"

resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(
  Resolver.ivyStylePatterns)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")

//addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.0.2")

addSbtPlugin("com.thoughtworks.sbt-api-mappings" % "sbt-api-mappings" % "2.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")//"1.1")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0-M1")//1.0.0")