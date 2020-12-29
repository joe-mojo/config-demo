#!/usr/bin/env sh
############################################
# Runtime image (app image) creator script #
############################################
#
# Create the final app image using multi-stage build
#

SBT_VERSION=$(grep "sbt.version" ../project/build.properties | cut -d '=' -f 2)
SCALA_VERSION_DIR=$(grep scalaVersion ../build.sbt | cut -d '=' -f 2 | cut -d '"' -f 2 | cut -d '.' -f 1-2)
APP_VERSION=$(grep version ../build.sbt | grep -v value | cut -d '=' -f 2 | cut -d '"' -f 2)

echo "Building config-demo-${APP_VERSION} image with: SBT_VERSION=${SBT_VERSION} and SCALA_VERSION_DIR=${SCALA_VERSION_DIR}"

docker build \
--build-arg BASE_IMAGE_TAG="11-jre-slim" \
--build-arg SCALA_VERSION_DIR=${SCALA_VERSION_DIR} \
--build-arg APP_VERSION=${APP_VERSION} \
-t joe-mojo/config-demo:${APP_VERSION} \
-f Dockerfile \
..
