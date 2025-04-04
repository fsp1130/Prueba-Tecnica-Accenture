# Archivo Dockerfile que permite crear la imagen ** empaqueta una aplicación con Docker

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/PruebaTecnicaAccenture-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]