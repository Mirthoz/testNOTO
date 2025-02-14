FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/transaction-producer.jar /app/transaction-producer.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "transaction-producer.jar"]