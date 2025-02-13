package com.example.common.dto;

import com.example.common.enumeration.Country;
import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.constraints.NotBlank;

public record TransactionDto(
    @NotBlank
    String userId,
    @NotBlank
    BigDecimal amount,
    @NotBlank
    Instant timestamp,
    @NotBlank
    Country country,
    @NotBlank
    double latitudeCoordinate,
    @NotBlank
    double longitudeCoordinate
) {}
