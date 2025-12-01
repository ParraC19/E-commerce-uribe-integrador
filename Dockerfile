# Etapa 1: Build de Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos todo el proyecto
COPY EcomerceUribe .

# Compilamos la aplicación
RUN cd EcomerceUribe && mvn -DskipTests clean package

# Etapa 2: Imagen liviana para correr el JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiamos el .jar generado
COPY --from=build /app/EcomerceUribe/target/EcomerceUribe-0.0.1-SNAPSHOT.jar app.jar

# Puerto donde Spring Boot escucha
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
