package com.example.common.exception;

public class FraudulentTransactionException extends RuntimeException {
  public FraudulentTransactionException(String message) {
    super(message);
  }
}