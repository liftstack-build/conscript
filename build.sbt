seq(conscriptSettings :_*)

organization := "net.databinder.conscript"

version := "0.3.5-SNAPSHOT"

name := "conscript"

publishTo := Some("Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

scalaVersion := "2.9.1"

libraryDependencies <<= (libraryDependencies, scalaVersion) {
  (deps, sv) => deps ++ Seq(
    "com.github.scopt" %% "scopt" % "1.1.2",
    "org.scala-lang" % "scala-swing" % sv
  )
}

seq(ProguardPlugin.proguardSettings :_*)

proguardOptions ++= Seq(
  "-keep class conscript.* { *; }",
  "-keep class org.apache.commons.logging.impl.LogFactoryImpl { *; }",
  "-keep class org.apache.commons.logging.impl.Jdk14Logger { *; }"
)

minJarPath <<= (target, version) { (t,v) =>
  t / ("conscript-" + v + ".jar")
}

seq(buildInfoSettings: _*)

sourceGenerators in Compile <+= buildInfo

buildInfoKeys := Seq[Scoped](name, version, scalaVersion, sbtVersion)

buildInfoPackage := "conscript"

version in dlj <<= version

organization in dlj <<= organization

publishTo in dlj <<= publishTo
