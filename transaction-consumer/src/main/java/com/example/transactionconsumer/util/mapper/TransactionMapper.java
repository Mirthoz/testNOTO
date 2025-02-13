package com.example.transactionconsumer.util.mapper;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.entity.Transaction;

public class TransactionMapper {

  public static Transaction TransactionDtoToTransaction(TransactionDto transactionDto){
    return new Transaction(
        null,
        transactionDto.userId(),
        transactionDto.amount(),
        transactionDto.timestamp(),
        transactionDto.country(),
        transactionDto.latitudeCoordinate(),
        transactionDto.longitudeCoordinate()
    );
  }
}
