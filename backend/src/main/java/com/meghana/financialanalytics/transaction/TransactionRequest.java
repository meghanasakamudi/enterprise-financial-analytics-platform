package com.meghana.financialanalytics.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        @NotNull Long customerId,
        @NotNull LocalDate transactionDate,
        @NotBlank String category,
        @NotNull @Positive BigDecimal amount,
        @NotBlank String transactionType
) {}
