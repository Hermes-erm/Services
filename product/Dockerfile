# stage 1 build
FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
#clean and package the java => jar file!!!
RUN mvn clean package

# stage 2
FROM openjdk:18
COPY --from=build /app/target/product-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java","-jar","product-0.0.1-SNAPSHOT.jar"]
