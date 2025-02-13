package com.example.common.exception;

public class MultipleCountriesTransactionException extends RuntimeException {
  public MultipleCountriesTransactionException() {
    super("User has created transactions in 3 different countries within the last 10 minutes.");
  }
}
