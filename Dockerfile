# Etapa 1: Build de Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos el proyecto Java (carpeta EcomerceUribe del repo) dentro de /app
COPY EcomerceUribe/ .

# Compilamos la aplicación
RUN mvn -DskipTests clean package

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el JAR generado desde la etapa de build
COPY --from=build /app/target/EcomerceUribe-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
