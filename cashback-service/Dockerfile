FROM openjdk:11
ARG JAR_FILE=target/cashback-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} cashback-service-1.0.jar
ENTRYPOINT ["java","-jar","/cashback-service-1.0.jar"]