package com.example.transactionconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.transactionconsumer.repository")
public class TransactionConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TransactionConsumerApplication.class, args);
  }

}
