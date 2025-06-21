FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:21
COPY --from=build /app/target/gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]