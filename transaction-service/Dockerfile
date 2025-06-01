FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/transaction-service.jar transaction-service.jar
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]
