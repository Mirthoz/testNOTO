package com.example.transactionconsumer.service.contract;

import com.example.common.dto.TransactionDto;
import com.example.common.enumeration.Country;
import com.example.common.exception.BlacklistedCountryTransactionException;
import com.example.common.exception.MultipleCountriesTransactionException;
import com.example.common.exception.SuspiciousTransactionLocationException;
import com.example.common.exception.TooManyTransactionsException;
import com.example.transactionconsumer.entity.Transaction;
import com.example.transactionconsumer.util.GeoUtils;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TransactionVerificationServiceImpl implements TransactionVerificationService{

  @Override
  public TransactionDto verifySuspiciousGeoTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast30Minutes
  ) {

    userTransactionsInLast30Minutes.sort(Comparator.comparing(Transaction::getTimestamp).reversed());

    if (userTransactionsInLast30Minutes.size() >= 2) {

      Transaction firstTransaction = userTransactionsInLast30Minutes.get(0);
      Transaction secondTransaction = userTransactionsInLast30Minutes.get(1);

      double firstLatitude = firstTransaction.getLatitudeCoordinate();
      double firstLongitude = firstTransaction.getLongitudeCoordinate();
      double secondLatitude = secondTransaction.getLatitudeCoordinate();
      double secondLongitude = secondTransaction.getLongitudeCoordinate();

      double distance = GeoUtils.calculateGeoDistance(
          firstLatitude,
          firstLongitude,
          secondLatitude,
          secondLongitude
      );

      if (distance > 300) {
        throw new SuspiciousTransactionLocationException();
      }
    }

    return transactionDto;
  }

  @Override
  public TransactionDto verifyMultiCountryTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast10Minutes
  ) {
    if (!userTransactionsInLast10Minutes.isEmpty()) {
      Set<Country> countries = new HashSet<>();

      for (Transaction transaction : userTransactionsInLast10Minutes) {
        countries.add(transaction.getCountry());
      }

      if (countries.size() > 3) {
        throw new MultipleCountriesTransactionException();
      }
    }

    return transactionDto;
  }

  @Override
  public TransactionDto verifyHighFrequencyTransactions(
      TransactionDto transactionDto,
      List<Transaction> userTransactionsInLast1Minute
  ) {
    if (userTransactionsInLast1Minute.size() > 10) {
      throw new TooManyTransactionsException();
    }

    return transactionDto;
  }

  @Override
  public TransactionDto verifyTransactionsInBlacklistedCountries(TransactionDto transactionDto) {
    if (transactionDto.country().isBlacklisted()){
      throw new BlacklistedCountryTransactionException();
    }

    return transactionDto;
  }
}
