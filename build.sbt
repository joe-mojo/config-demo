import Dependencies._

scalacOptions += "-Xmacro-settings:materialize-derivations"

lazy val root = (project in file(".")).
		settings(
			inThisBuild(List(
				organization := "org.jro",
				scalaVersion := "2.13.3",
				version      := "0.1.0"
			)),
			name := "config-demo",
			scalacOptions ++= Seq(
				"-language:postfixOps",
				"-Xmacro-settings:materialize-derivations"
			),
			libraryDependencies ++= Seq(
				playJson,
				lightbendConfig,
				pureconfig,
				scalaTest % Test
			)
		)
