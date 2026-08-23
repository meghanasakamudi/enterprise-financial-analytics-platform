package com.meghana.financialanalytics.analytics;

import java.math.BigDecimal;

public record AnalyticsSummary(
        BigDecimal revenue,
        BigDecimal expenses,
        BigDecimal netIncome,
        BigDecimal operatingMarginPercent,
        long totalCustomers,
        long activeCustomers
) {}
