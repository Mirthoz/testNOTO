package com.example.common.exception;

public class BlacklistedCountryTransactionException extends RuntimeException {
  public BlacklistedCountryTransactionException() {
    super("Transaction created within the territory of a blacklisted country.");
  }
}
