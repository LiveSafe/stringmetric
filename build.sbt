//enablePlugins(BuildInfoPlugin)
//buildInfoKeys := Seq(name, version, scalaVersion, sbtVersion)
//buildInfoPackage := "com.livesafe.common"
//buildInfoUsePackageAsPath := true
lazy val root = Project("stringmetric", file("."))
	.settings(
		name := "stringmetric",
		organization := "livesafe",
		scalaVersion := "2.12.5",
		crossPaths := true,
		publishArtifact in Test := false,
		releaseCrossBuild := true,
		crossScalaVersions := Seq("2.12.5", "2.11.8"),
		crossVersion := CrossVersion.binary
	)
	.aggregate(core, cli)

val specs2deps = Seq(
  "org.specs2" %% "specs2-core" % "4.2.0" % "test"
)

lazy val core: Project = Project("core", file("core"),
	settings = (root.settings: Seq[sbt.Def.Setting[_]]) ++ Seq(
		libraryDependencies ++= specs2deps,
		name := "stringmetric-core"
	)
)

lazy val cli: Project = Project("cli", file("cli"),
	settings = (root.settings: Seq[sbt.Def.Setting[_]]) ++ Seq(
		libraryDependencies ++= specs2deps,
		name := "stringmetric-cli"
	)
).dependsOn(core)

credentials in ThisBuild ++=
	(for (un <- sys.env.get("LIVESAFE_ARTIFACTORY_USERNAME"); pw <- sys.env.get("LIVESAFE_ARTIFACTORY_PASSWORD")) yield Credentials("Artifactory Realm", "livesafe.jfrog.io", un, pw)).toList :+
		Credentials(Path.userHome / ".livesafe" / "credentials.properties")

publishMavenStyle in ThisBuild := true
publishTo in ThisBuild := Some("LiveSafe Internal (Maven, local)" at "https://livesafe.jfrog.io/livesafe/livesafe-internal-maven-local")
resolvers in ThisBuild += "LiveSafe Engineering (Maven)" at "https://livesafe.jfrog.io/livesafe/livesafe-engineering-generic"
