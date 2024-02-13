ARG BASE_IMAGE_TAG="21-jre-slim"
FROM joe-mojo/scala-sbt:latest AS builder
WORKDIR /config-demo/
COPY build.sbt build.sbt
COPY ./src src/
COPY ./project/ project/
RUN rm -rf project/target && sbt assembly

ENV BASE_IMAGE_TAG=${BASE_IMAGE_TAG}
FROM docker.io/openjdk:${BASE_IMAGE_TAG}
ARG CONFIG_PATH="/config-demo/config/application-runtime.conf"
ARG SCALA_VERSION_DIR
ARG APP_VERSION
ARG BASE_IMAGE_TAG
ARG CONFIG_FILE=application-runtime.conf
ARG CONFIG_DIR=/config-demo/config/
ENV SCALA_VERSION_DIR=${SCALA_VERSION_DIR}
ENV APP_VERSION=${APP_VERSION}
ENV CONFIG_PATH=${CONFIG_DIR}/${CONFIG_FILE}

# DEBUG RUN echo "Building runtime image from openjdk:${BASE_IMAGE_TAG} for app version ${APP_VERSION} built with Scala dir ${SCALA_VERSION_DIR}"
# ARG USER_ID
# ARG GROUP_ID
WORKDIR /config-demo/
COPY --from=builder /config-demo/target/scala-${SCALA_VERSION_DIR}/config-demo-${APP_VERSION}-with-dependencies.jar config-demo-${APP_VERSION}-with-dependencies.jar

ENTRYPOINT java -XX:MinRAMPercentage=60 -XX:MaxRAMPercentage=85 -Dconfig.file=${CONFIG_PATH} -jar /config-demo/config-demo-${APP_VERSION}-with-dependencies.jar
