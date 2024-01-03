FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV SPRING_OPTS=
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/microservice-0.0.1-SNAPSHOT.jar albumsandfotos-microservice.jar
EXPOSE 3000
ENTRYPOINT exec java ${JAVA_OPTS} -jar albumsandfotos-microservice.jar ${SPRING_OPTS}
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar albumsandfotos.jar
