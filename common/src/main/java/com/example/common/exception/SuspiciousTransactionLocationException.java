package com.example.common.exception;

public class SuspiciousTransactionLocationException extends RuntimeException {
  public SuspiciousTransactionLocationException() {
    super("Two user transactions created from different places on Earth within the last 30 minutes and the distance between them is more than 300 kilometers.");
  }
}
