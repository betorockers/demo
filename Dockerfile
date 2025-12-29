# Etapa 1: Construcción (Build)
# Usamos una imagen con Maven y JDK 21 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Empaquetamos el jar saltando los tests (ya los corrimos antes)
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
# Usamos una imagen ligera solo con JRE 21 para correr la app
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]