resolvers ++=
  ("Era7 (releases)" at "https://s3-eu-west-1.amazonaws.com/releases.era7.com") ::
    Nil

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.7.0")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.5")
addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.16.0")
