#!/usr/bin/env sh
##############################
# Build image creator script #
##############################
#
# create an sbt buidler
#

SBT_VERSION=$(grep "sbt.version" ../project/build.properties | cut -d '=' -f 2)
SCALA_VERSION=$(grep scalaVersion ../build.sbt | cut -d '=' -f 2 | cut -d '"' -f 2)

echo "Building scala-sbt image with: SBT_VERSION=$SBT_VERSION and SCALA_VERSION=$SCALA_VERSION"

docker build \
--build-arg BASE_IMAGE_TAG="21-jdk-slim" \
--build-arg SBT_VERSION="${SBT_VERSION}" \
--build-arg SCALA_VERSION="${SCALA_VERSION}" \
--build-arg USER_ID=1001 \
--build-arg GROUP_ID=1001 \
-t joe-mojo/scala-sbt \
-f builder.Dockerfile \
.
