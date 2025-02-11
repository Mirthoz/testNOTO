package com.example.transactionconsumer.service.contract;

import com.example.common.dto.TransactionDto;

public interface ConsumerService {

  void processTransaction(TransactionDto transactionDto);

}
