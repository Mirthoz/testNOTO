package com.example.transactionconsumer.service;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.entity.Transaction;
import com.example.transactionconsumer.repository.FraudTransactionRepository;
import com.example.transactionconsumer.repository.TransactionRepository;
import com.example.transactionconsumer.service.contract.ProcessTransactionFactoryService;
import com.example.transactionconsumer.service.contract.TransactionVerificationService;
import com.example.transactionconsumer.util.mapper.FraudTransactionMapper;
import com.example.transactionconsumer.util.mapper.TransactionMapper;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProcessTransactionFactoryServiceImpl implements ProcessTransactionFactoryService {

  private final TransactionRepository transactionRepository;
  private final FraudTransactionRepository fraudTransactionRepository;
  private final TransactionVerificationService transactionVerificationService;

  public ProcessTransactionFactoryServiceImpl(TransactionRepository transactionRepository,
      FraudTransactionRepository fraudTransactionRepository,
      TransactionVerificationService transactionVerificationService) {
    this.transactionRepository = transactionRepository;
    this.fraudTransactionRepository = fraudTransactionRepository;
    this.transactionVerificationService = transactionVerificationService;
  }

  @Override
  public void processTransactionFactory(TransactionDto transactionDto) {
    Instant tenMinutesAgo = Instant.now().minusSeconds(10 * 60);
    Instant minuteAgo = Instant.now().minusSeconds(60);

    List<Transaction> userTransactionsLast30Minutes = transactionRepository
        .findByUserIdAndTimestampAfter(
            transactionDto.userId(),
            Instant.now().minusSeconds(30 * 60)
        );

    try{
      transactionVerificationService.verifyTransactionsInBlacklistedCountries(transactionDto);

      transactionVerificationService.verifySuspiciousGeoTransactions(
          transactionDto, userTransactionsLast30Minutes
      );

      List<Transaction> userTransactionsLast10Minutes = userTransactionsLast30Minutes.stream()
          .filter(transaction -> transaction.getTimestamp().isAfter(tenMinutesAgo))
          .toList();

      transactionVerificationService.verifyMultiCountryTransactions(
          transactionDto, userTransactionsLast10Minutes
      );

      List<Transaction> userTransactionsLast1Minute = userTransactionsLast30Minutes.stream()
          .filter(transaction -> transaction.getTimestamp().isAfter(minuteAgo))
          .toList();

      transactionVerificationService.verifyHighFrequencyTransactions(
          transactionDto, userTransactionsLast1Minute
      );

      transactionRepository.save(TransactionMapper.TransactionDtoToTransaction(transactionDto));

    } catch (RuntimeException e){
      fraudTransactionRepository.save(
          FraudTransactionMapper.TransactionDtoToFraudTransaction(transactionDto, e.getMessage())
      );
    }
  }
}
