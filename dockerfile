# Usar una imagen base de Maven con JDK 17
FROM maven:3.8.4-openjdk-17 AS build

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo pom.xml y los archivos de dependencias
COPY pom.xml ./
COPY src ./src

# Compilar y empaquetar la aplicación utilizando Maven
RUN mvn clean package -DskipTests

# Usar una imagen base de OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Crear un directorio para la aplicación en el contenedor
WORKDIR /app

# Copiar el jar generado desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto 8080 (puerto por defecto de Spring Boot)
EXPOSE 8080

# Establecer el comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
