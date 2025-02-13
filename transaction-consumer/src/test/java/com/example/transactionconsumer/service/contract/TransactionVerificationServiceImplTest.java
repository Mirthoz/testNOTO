package com.example.transactionconsumer.service.contract;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.common.dto.TransactionDto;
import com.example.common.enumeration.Country;
import com.example.common.exception.BlacklistedCountryTransactionException;
import com.example.common.exception.MultipleCountriesTransactionException;
import com.example.common.exception.SuspiciousTransactionLocationException;
import com.example.common.exception.TooManyTransactionsException;
import com.example.transactionconsumer.entity.Transaction;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionVerificationServiceImplTest {

  @InjectMocks
  private TransactionVerificationServiceImpl transactionVerificationService;

  @Mock
  private TransactionDto transactionDto;

  @Mock
  private List<Transaction> userTransactionsInLast30Minutes;

  @Mock
  private List<Transaction> userTransactionsInLast1Minute;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testVerifySuspiciousGeoTransactions_ShouldThrowException_WhenDistanceExceeds300() {
    // Given
    Transaction transaction1 = new Transaction(52.5200, 13.4050, Instant.now()); // Berlin
    Transaction transaction2 = new Transaction(48.8566, 2.3522, Instant.now().minusSeconds(600)); // Paris

    when(userTransactionsInLast30Minutes.size()).thenReturn(2);
    when(userTransactionsInLast30Minutes.get(0)).thenReturn(transaction1);
    when(userTransactionsInLast30Minutes.get(1)).thenReturn(transaction2);

    // When & Then
    assertThrows(SuspiciousTransactionLocationException.class, () -> {
      transactionVerificationService.verifySuspiciousGeoTransactions(transactionDto, userTransactionsInLast30Minutes);
    });
}

  @Test
  public void testVerifySuspiciousGeoTransactions_ShouldNotThrowException_WhenDistanceIsWithin300() {
    // Given
    Transaction transaction1 = new Transaction(52.5200, 13.4050, Instant.now()); // Berlin
    Transaction transaction2 = new Transaction(53.5511, 9.9937, Instant.now().minusSeconds(600)); // Hamburg

    List userTransactionsInLast30Minutes = mock(List.class);

    when(userTransactionsInLast30Minutes.size()).thenReturn(2);
    when(userTransactionsInLast30Minutes.get(0)).thenReturn(transaction1);
    when(userTransactionsInLast30Minutes.get(1)).thenReturn(transaction2);

    // When
    TransactionDto result = transactionVerificationService.verifySuspiciousGeoTransactions(transactionDto, userTransactionsInLast30Minutes);

    // Then
    assertEquals(transactionDto, result);
  }

  @Test
  public void testVerifySuspiciousGeoTransactions_ShouldReturnTransactionDto_WhenValid() {
    // Given
    Transaction transaction1 = new Transaction(52.5200, 13.4050, Instant.now()); // Berlin
    Transaction transaction2 = new Transaction(53.5511, 9.9937, Instant.now().minusSeconds(600)); // Hamburg

    when(userTransactionsInLast30Minutes.size()).thenReturn(2);
    when(userTransactionsInLast30Minutes.get(0)).thenReturn(transaction1);
    when(userTransactionsInLast30Minutes.get(1)).thenReturn(transaction2);

    // When
    TransactionDto result = transactionVerificationService.verifySuspiciousGeoTransactions(transactionDto, userTransactionsInLast30Minutes);

    // Then
    assertNotNull(result);
  }

  @Test
  public void testVerifyTransactionsInBlacklistedCountries_ShouldThrowException_WhenCountryIsBlacklisted() {
    // Given
    when(transactionDto.country()).thenReturn(Country.AZERBAIJAN);

    // When & Then
    assertThrows(BlacklistedCountryTransactionException.class, () -> {
      transactionVerificationService.verifyTransactionsInBlacklistedCountries(transactionDto);
    });
  }

  @Test
  public void testVerifyTransactionsInBlacklistedCountries_ShouldNotThrowException_WhenCountryIsNotBlacklisted() {
    // Given
    when(transactionDto.country()).thenReturn(Country.AUSTRIA);

    // When
    TransactionDto result = transactionVerificationService.verifyTransactionsInBlacklistedCountries(transactionDto);

    // Then
    assertEquals(transactionDto, result);
  }

  @Test
  public void testVerifyTransactionsInBlacklistedCountries_ShouldReturnTransactionDto_WhenValid() {
    // Given
    when(transactionDto.country()).thenReturn(Country.GERMANY);

    // When
    TransactionDto result = transactionVerificationService.verifyTransactionsInBlacklistedCountries(transactionDto);

    // Then
    assertNotNull(result);
  }

  @Test
  public void testVerifyMultiCountryTransactions_ShouldThrowException_WhenMoreThanThreeCountries() {
    // Given
    Transaction transaction1 = new Transaction(Country.BULGARIA, Instant.now());
    Transaction transaction2 = new Transaction(Country.GERMANY, Instant.now().minusSeconds(300));
    Transaction transaction3 = new Transaction(Country.FRANCE, Instant.now().minusSeconds(600));
    Transaction transaction4 = new Transaction(Country.CANADA, Instant.now().minusSeconds(900));

    List<Transaction> userTransactionsInLast10Minutes = List.of(transaction1, transaction2, transaction3, transaction4);

    // When & Then
    assertThrows(MultipleCountriesTransactionException.class, () -> {
      transactionVerificationService.verifyMultiCountryTransactions(transactionDto, userTransactionsInLast10Minutes);
    });
  }

  @Test
  public void testVerifyMultiCountryTransactions_ShouldNotThrowException_WhenThreeOrLessCountries() {
    // Given
    Transaction transaction1 = new Transaction(Country.BULGARIA, Instant.now());
    Transaction transaction2 = new Transaction(Country.GERMANY, Instant.now().minusSeconds(300));
    Transaction transaction3 = new Transaction(Country.FRANCE, Instant.now().minusSeconds(600));
    List<Transaction> userTransactionsInLast10Minutes = List.of(transaction1, transaction2, transaction3);

    // When
    TransactionDto result = transactionVerificationService.verifyMultiCountryTransactions(transactionDto, userTransactionsInLast10Minutes);

    // Then
    assertEquals(transactionDto, result);
  }

  @Test
  public void testVerifyMultiCountryTransactions_ShouldReturnTransactionDto_WhenValid() {
    // Given
    Transaction transaction1 = new Transaction(Country.BULGARIA, Instant.now());
    Transaction transaction2 = new Transaction(Country.GERMANY, Instant.now().minusSeconds(300));
    List<Transaction> userTransactionsInLast10Minutes = List.of(transaction1, transaction2);

    // When
    TransactionDto result = transactionVerificationService.verifyMultiCountryTransactions(transactionDto, userTransactionsInLast10Minutes);

    // Then
    assertNotNull(result);
  }

  @Test
  public void testVerifyHighFrequencyTransactions_ShouldThrowException_WhenMoreThanTenTransactions() {
    // Given
    when(userTransactionsInLast1Minute.size()).thenReturn(11);

    // When & Then
    assertThrows(TooManyTransactionsException.class, () -> {
      transactionVerificationService.verifyHighFrequencyTransactions(transactionDto, userTransactionsInLast1Minute);
    });
  }

  @Test
  public void testVerifyHighFrequencyTransactions_ShouldNotThrowException_WhenTenOrLessTransactions() {
    // Given
    when(userTransactionsInLast1Minute.size()).thenReturn(10);

    // When
    TransactionDto result = transactionVerificationService.verifyHighFrequencyTransactions(transactionDto, userTransactionsInLast1Minute);

    // Then
    assertEquals(transactionDto, result);
  }

  @Test
  public void testVerifyHighFrequencyTransactions_ShouldReturnTransactionDto_WhenValid() {
    // Given
    when(userTransactionsInLast1Minute.size()).thenReturn(5);

    // When
    TransactionDto result = transactionVerificationService.verifyHighFrequencyTransactions(transactionDto, userTransactionsInLast1Minute);

    // Then
    assertNotNull(result);
  }
}
