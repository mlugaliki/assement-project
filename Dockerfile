FROM openjdk:21

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE

COPY target/kcb-project-app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT exec java \
 -Djava.security.egd=file:/dev/./urandom \
 -jar app.jar
