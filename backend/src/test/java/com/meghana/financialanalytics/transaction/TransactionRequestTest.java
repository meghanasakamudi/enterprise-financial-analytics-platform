package com.meghana.financialanalytics.transaction;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionRequestTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void acceptsValidTransaction() {
        var request = new TransactionRequest(1L, LocalDate.now(), "Platform Services", new BigDecimal("1250.00"), "REVENUE");
        assertThat(validator.validate(request)).isEmpty();
    }

    @Test
    void rejectsNonPositiveAmount() {
        var request = new TransactionRequest(1L, LocalDate.now(), "Platform Services", BigDecimal.ZERO, "REVENUE");
        assertThat(validator.validate(request)).anyMatch(v -> v.getPropertyPath().toString().equals("amount"));
    }
}
