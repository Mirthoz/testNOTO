package com.example.transactionconsumer.repository;

import com.example.transactionconsumer.entity.Transaction;
import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

  List<Transaction> findByUserIdAndTimestampAfter(String userId, Instant timestamp);
}
