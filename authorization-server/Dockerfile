FROM openjdk:11
ARG JAR_FILE=target/authorization-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} authorization-server-1.0.jar
ENTRYPOINT ["java","-jar","/authorization-server-1.0.jar"]