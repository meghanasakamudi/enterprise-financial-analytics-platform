package com.meghana.financialanalytics.analytics;

import com.meghana.financialanalytics.customer.CustomerRepository;
import com.meghana.financialanalytics.transaction.FinancialTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {
    private final FinancialTransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public AnalyticsService(FinancialTransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public AnalyticsSummary summary(LocalDate start, LocalDate end) {
        BigDecimal revenue = transactionRepository.totalByTypeAndDateRange("REVENUE", start, end);
        BigDecimal expenses = transactionRepository.totalByTypeAndDateRange("EXPENSE", start, end);
        BigDecimal netIncome = revenue.subtract(expenses);
        BigDecimal margin = revenue.signum() == 0 ? BigDecimal.ZERO
                : netIncome.multiply(BigDecimal.valueOf(100)).divide(revenue, 2, RoundingMode.HALF_UP);
        return new AnalyticsSummary(revenue, expenses, netIncome, margin, customerRepository.count(), transactionRepository.activeCustomers(start, end));
    }

    public List<MonthlyMetric> monthlyTrend(int months) {
        int safeMonths = Math.max(1, Math.min(months, 24));
        YearMonth current = YearMonth.now();
        List<MonthlyMetric> result = new ArrayList<>();
        for (int offset = safeMonths - 1; offset >= 0; offset--) {
            YearMonth month = current.minusMonths(offset);
            LocalDate start = month.atDay(1);
            LocalDate end = month.atEndOfMonth();
            BigDecimal revenue = transactionRepository.totalByTypeAndDateRange("REVENUE", start, end);
            BigDecimal expenses = transactionRepository.totalByTypeAndDateRange("EXPENSE", start, end);
            result.add(new MonthlyMetric(month.format(DateTimeFormatter.ofPattern("MMM yyyy")), revenue, expenses, revenue.subtract(expenses)));
        }
        return result;
    }
}
