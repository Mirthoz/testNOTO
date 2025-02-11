package com.example.common.dto;

public record TransactionDto(
    String tranId,
    String userId,
    double amount,
    long timestamp,
    String country,
    double latCoord,
    double longCoord) {}
