FROM openjdk:11
ARG JAR_FILE=target/gateway-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gateway-server-1.0.jar
ENTRYPOINT ["java","-jar","/gateway-server-1.0.jar"]