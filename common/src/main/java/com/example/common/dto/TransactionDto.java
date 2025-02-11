package com.example.common.dto;

import com.example.common.enumeration.Country;

public record TransactionDto(
    String tranId,
    String userId,
    double amount,
    long timestamp,
    Country country,
    double latCoord,
    double longCoord) {}
