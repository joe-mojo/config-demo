package org.jro.configdemo.config

import scala.concurrent.duration.Duration

final case class ConsumerConf(bootstrapServers: Seq[HostPort], groupId: String, sessionTimeout: Duration)
