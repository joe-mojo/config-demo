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
			),
			assembly / mainClass := Some("org.jro.configdemo.Main"),
			assembly / test := {},
			assembly / assemblyMergeStrategy := {
				case "reference.conf"			=> MergeStrategy.concat
				case "application-fixed.conf"	=> MergeStrategy.concat
				case "application-runtime.conf"	=> MergeStrategy.discard
				case x =>
					val oldStrategy = (assemblyMergeStrategy in assembly).value
					oldStrategy(x)
			},
			assembly / assemblyJarName := s"${name.value}-${version.value}-with-dependencies.jar"
		)
