package com.example.common.dto;

import com.example.common.enumeration.Country;
import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDto(
    String transactionId,
    String userId,
    BigDecimal amount,
    Instant timestamp,
    Country country,
    double latitudeCoordinate,
    double longitudeCoordinate) {}
