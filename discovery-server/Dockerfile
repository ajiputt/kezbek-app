FROM openjdk:11
ARG JAR_FILE=target/discovery-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} discovery-server-1.0.jar
ENTRYPOINT ["java","-jar","/discovery-server-1.0.jar"]