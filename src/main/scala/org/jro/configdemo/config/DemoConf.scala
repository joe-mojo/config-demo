package org.jro.configdemo.config

final case class DemoConf(appName: String, instanceId: String, kafka: KafkaConf)
