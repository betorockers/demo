# Etapa 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Empaquetar saltando tests para agilizar el build en demo
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto y ejecutar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]