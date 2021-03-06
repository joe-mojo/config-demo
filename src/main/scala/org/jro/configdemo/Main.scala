package org.jro.configdemo

import com.typesafe.config.{ConfigFactory, ConfigRenderOptions}
import org.jro.configdemo.config._
import pureconfig._
import pureconfig.generic.auto._

/**
 *
 */
object Main extends App {
	val configNameSpace = "org.jro.scratchpad.configdemo"
	val configRendering: ConfigRenderOptions = ConfigRenderOptions.defaults().setFormatted(true)
	val config = ConfigFactory.load().withFallback(ConfigFactory.load("application-fixed.conf"))
	println(s"lightbend config for $configNameSpace: ${config.getConfig(configNameSpace).root().render(configRendering)}")

	val demoConfigOrErrors = ConfigSource.fromConfig(config).at(configNameSpace).load[DemoConf]

	demoConfigOrErrors match {
		case Right(demoConfig) =>
			println(s"demoConfig = $demoConfig")
		case Left(errors) =>
			println(s"Errors found inRules demoConfig: $errors")
	}
}
