package com.example.transactionconsumer.service.contract;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.entity.Transaction;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionVerificationServiceImpl implements TransactionVerificationService{

  @Override
  public TransactionDto verifySuspiciousGeoTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast30Minutes
  ) {

    if (!userTransactionsInLast30Minutes.isEmpty()){

    }
    return transactionDto;
  }

  @Override
  public TransactionDto verifyMultiCountryTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast10Minutes
  ) {

    if (!userTransactionsInLast10Minutes.isEmpty()){

    }

    return transactionDto;
  }

  @Override
  public TransactionDto verifyHighFrequencyTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast1Minute
  ) {

    if (!userTransactionsInLast1Minute.isEmpty()){

    }
    return transactionDto;
  }

  @Override
  public TransactionDto verifyTransactionsInBlacklistedCountries(TransactionDto transactionDto) {
    return transactionDto;
  }
}
