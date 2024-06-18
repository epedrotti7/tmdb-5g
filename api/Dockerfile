FROM maven:3.6.3-jdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:11-jre-slim
WORKDIR /app

RUN apt-get update && apt-get install -y netcat

COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

CMD ["sh", "-c", "./wait-for-it.sh mysql_container -- java -jar app.jar"]