# Build stage
FROM ghcr.io/graalvm/graalvm-community:21 AS build
WORKDIR /app

# Copy maven wrapper and pom file
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copy source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Run stage
FROM ghcr.io/graalvm/jdk-community:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
