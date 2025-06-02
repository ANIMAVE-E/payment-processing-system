FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/payment-service.jar payment-service.jar
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar payment-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
