# Configura la imagen base de Java desde Eclipse Temurin (anteriormente adoptopenjdk)
FROM eclipse-temurin:latest
#docker pull eclipse-temurin:latest

# Copia el archivo JAR generado por Maven al contenedor
COPY target/project-proposal-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta tu aplicación Spring Boot
#EXPOSE 8080

# Comando para ejecutar tu aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
