#!/usr/bin/env sh

if [ "$1" = "" ]
then
  APP_VERSION=$(grep version ../build.sbt | grep -v value | cut -d '=' -f 2 | cut -d '"' -f 2)
else
  APP_VERSION=$1
fi

mkdir -p /tmp/config-demo-volumes/config-volume && \
cp ../src/main/resources/application-runtime.conf /tmp/config-demo-volumes/config-volume/ && \
docker run -v /tmp/config-demo-volumes/config-volume:/config-demo/config joe-mojo/config-demo:$APP_VERSION