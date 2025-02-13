package com.example.transactionconsumer.util.mapper;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.entity.FraudTransaction;

public class FraudTransactionMapper {

  public static FraudTransaction TransactionDtoToFraudTransaction(
      TransactionDto transactionDto, String reasonForRejection
  ){
    return new FraudTransaction(
        null,
        transactionDto.userId(),
        transactionDto.amount(),
        transactionDto.timestamp(),
        transactionDto.country(),
        transactionDto.latitudeCoordinate(),
        transactionDto.longitudeCoordinate(),
        reasonForRejection
    );
  }
}
