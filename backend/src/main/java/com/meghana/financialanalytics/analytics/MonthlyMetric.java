package com.meghana.financialanalytics.analytics;

import java.math.BigDecimal;

public record MonthlyMetric(String month, BigDecimal revenue, BigDecimal expenses, BigDecimal netIncome) {}
