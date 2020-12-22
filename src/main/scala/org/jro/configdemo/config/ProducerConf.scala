package org.jro.configdemo.config

final case class ProducerConf(bootstrapServers: Seq[HostPort], clientId: String, acks: String, retries: Int, maxInFlightRequests: Int)
