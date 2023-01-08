FROM openjdk:11
ARG JAR_FILE=target/config-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} config-server-1.0.jar
ENTRYPOINT ["java","-jar","/config-server-1.0.jar"]