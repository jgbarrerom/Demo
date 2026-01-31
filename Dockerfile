# Build stage
FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /demo
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-ea-1-jdk-slim
ENV MYSQ_HOST=172.18.0.2
ENV MYSQL_PORT=3306
ENV DATABASE_NAME=test
WORKDIR /demo
COPY --from=build /demo/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]