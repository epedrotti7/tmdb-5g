# Dockerfile for API

# Etapa de construção
FROM maven:3.6.3-jdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn clean install

# Etapa final
FROM openjdk:11-jre-slim
WORKDIR /app

# Instalar netcat
RUN apt-get update && apt-get install -y netcat

# Copiar o script wait-for-it.sh
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# Copiar o JAR da etapa de construção
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

# Comando de inicialização com um atraso
CMD ["sh", "-c", "./wait-for-it.sh mysql_container -- java -jar app.jar"]