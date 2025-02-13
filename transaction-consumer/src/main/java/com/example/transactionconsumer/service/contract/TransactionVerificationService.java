package com.example.transactionconsumer.service.contract;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.entity.Transaction;
import java.util.List;

public interface TransactionVerificationService {

  TransactionDto verifyHighFrequencyTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast1Minute
  );

  TransactionDto verifySuspiciousGeoTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast30Minutes
  );

  TransactionDto verifyTransactionsInBlacklistedCountries(TransactionDto transactionDto);

  TransactionDto verifyMultiCountryTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast10Minutes
  );
}
