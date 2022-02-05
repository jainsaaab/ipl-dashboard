FROM openjdk:17-oracle

RUN groupadd spring && useradd spring -g spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "./app.jar" ]