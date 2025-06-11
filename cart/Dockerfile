FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:18
COPY --from=build /app/target/cart-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD ["java", "-jar", "cart-0.0.1-SNAPSHOT.jar"]