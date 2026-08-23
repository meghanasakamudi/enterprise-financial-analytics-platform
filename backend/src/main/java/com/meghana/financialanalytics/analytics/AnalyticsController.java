package com.meghana.financialanalytics.analytics;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:4200")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) { this.analyticsService = analyticsService; }

    @GetMapping("/summary")
    public AnalyticsSummary summary(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        LocalDate resolvedEnd = end == null ? LocalDate.now() : end;
        LocalDate resolvedStart = start == null ? resolvedEnd.minusMonths(6).withDayOfMonth(1) : start;
        if (resolvedStart.isAfter(resolvedEnd)) throw new IllegalArgumentException("start must be on or before end");
        return analyticsService.summary(resolvedStart, resolvedEnd);
    }

    @GetMapping("/monthly")
    public List<MonthlyMetric> monthly(@RequestParam(defaultValue = "6") int months) {
        return analyticsService.monthlyTrend(months);
    }
}
