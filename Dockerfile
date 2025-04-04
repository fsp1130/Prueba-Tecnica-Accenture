# Usamos una imagen base de Java
FROM openjdk:17-jdk-slim

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiamos el JAR generado
COPY target/PruebaTecnicaAccenture-0.0.1-SNAPSHOT.jar app.jar

# Puerto que expone tu app (el mismo que usas en application.properties, por defecto 8080)
EXPOSE 8080

# Comando para ejecutar tu app
ENTRYPOINT ["java", "-jar", "app.jar"]