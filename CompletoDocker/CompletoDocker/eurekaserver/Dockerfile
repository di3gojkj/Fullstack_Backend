#Stage 1: compilacion del proyecto con Maven
FROM eclipse-temurin:21-jdk-alpine AS build
#Instalar el gestor de maven ya que alpine no lo trae 
RUN apk add --no-cache maven
#Indicar el directorio de trabajo dentro del contenedor
WORKDIR /app
#Copiar la configuracion que tiene el proyecto
COPY pom.xml .
#Descargar las dependencias de Maven pero sin compilar
RUN mvn dependency:go-offline -B -q
#Copiar el codigo fuente
COPY src ./src
#Compilar y empaquetar --- No incluir los Test
RUN mvn package -DskipTests -B -q

#Stage 2: Ejecutar la imagen de manera liviana
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
#Copiar el JAR de la app
COPY --from=build /app/target/*.jar app.jar
#Documento el puerto de salida pero sin abrirlo directamente
EXPOSE 8761
#Cual es la imagen empaquetada 
ENTRYPOINT [ "java", "-jar", "app.jar" ]
