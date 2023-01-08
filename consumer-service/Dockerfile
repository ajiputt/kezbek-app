FROM openjdk:11
ARG JAR_FILE=target/consumer-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} consumer-service-1.0.jar
ENTRYPOINT ["java","-jar","/consumer-service-1.0.jar"]