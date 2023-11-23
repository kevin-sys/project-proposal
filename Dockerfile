# Usa una imagen base de Java
FROM adoptopenjdk/openjdk11:alpine

# Copia el archivo JAR generado al contenedor
COPY target/project-proposal-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto en el que tu aplicación Spring Boot está ejecutando
EXPOSE 8080

# Comando para ejecutar tu aplicación Spring Boot cuando el contenedor se inicie
CMD ["java", "-jar", "/app/app.jar"]
