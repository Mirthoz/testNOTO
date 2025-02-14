FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/transaction-consumer.jar /app/transaction-consumer.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "transaction-consumer.jar"]