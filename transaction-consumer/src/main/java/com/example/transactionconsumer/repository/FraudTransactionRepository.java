package com.example.transactionconsumer.repository;

import com.example.transactionconsumer.entity.FraudTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudTransactionRepository extends MongoRepository<FraudTransaction, String> {

}
