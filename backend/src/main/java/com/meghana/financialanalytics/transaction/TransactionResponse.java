package com.meghana.financialanalytics.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        Long customerId,
        String customerName,
        LocalDate transactionDate,
        String category,
        BigDecimal amount,
        String transactionType
) {
    public static TransactionResponse from(FinancialTransaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getCustomer().getId(),
                transaction.getCustomer().getName(),
                transaction.getTransactionDate(),
                transaction.getCategory(),
                transaction.getAmount(),
                transaction.getTransactionType()
        );
    }
}
