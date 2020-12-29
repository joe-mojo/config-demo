#!/usr/bin/env sh
mkdir -p /tmp/config-demo-volumes/config-volume && \
cp ../src/main/resources/application-runtime.conf /tmp/config-demo-volumes/config-volume/ && \
docker run -v /tmp/config-demo-volumes/config-volume:/config-demo/config joe-mojo/config-demo:0.1.0