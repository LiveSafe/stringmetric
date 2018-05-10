//enablePlugins(BuildInfoPlugin)
//buildInfoKeys := Seq(name, version, scalaVersion, sbtVersion)
//buildInfoPackage := "com.livesafe.common"
//buildInfoUsePackageAsPath := true
s3region := com.amazonaws.regions.Regions.US_EAST_1
lazy val root = Project("stringmetric", file("."))
	.settings(
		name := "stringmetric",
		organization := "livesafe",
		publishMavenStyle := false,
		scalaVersion := "2.12.5",
		crossPaths := true,
		publishArtifact in Test := false,
		releaseCrossBuild := true,
		crossScalaVersions := Seq("2.12.5", "2.11.8"),
		publishTo := Some(s3resolver.value("LiveSafe", s3("livesafe-artifacts/ivy2")).withIvyPatterns),
		crossVersion := CrossVersion.binary,
		version := "0.28.0"
	)
	.aggregate(core, cli)

val specs2deps = Seq(
  "org.specs2" %% "specs2-core" % "2.5.0" % "test",
  "org.specs2" %% "specs2-junit" % "2.5.0" % "test"
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
