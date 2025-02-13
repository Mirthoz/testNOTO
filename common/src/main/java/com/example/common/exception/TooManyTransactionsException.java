package com.example.common.exception;

public class TooManyTransactionsException extends RuntimeException {
  public TooManyTransactionsException() {
    super("User has created more than 10 transactions within the last minute.");
  }
}
