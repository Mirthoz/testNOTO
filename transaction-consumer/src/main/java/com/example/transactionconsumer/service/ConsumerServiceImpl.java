package com.example.transactionconsumer.service;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.service.contract.ConsumerService;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

  @Override
  public void processTransaction(TransactionDto transactionDto) {

  }
}
