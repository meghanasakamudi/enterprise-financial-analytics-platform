import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { forkJoin } from 'rxjs';
import { CustomerService } from './services/customer.service';
import { AnalyticsService } from './services/analytics.service';
import { Customer } from './models/customer';
import { AnalyticsSummary, MonthlyMetric } from './models/analytics';

interface Kpi { label: string; value: string; detail: string; }

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  customers: Customer[] = [];
  monthlyTrend: MonthlyMetric[] = [];
  kpis: Kpi[] = [];
  loading = true;
  apiAvailable = true;

  constructor(
    private readonly customerService: CustomerService,
    private readonly analyticsService: AnalyticsService
  ) {}

  ngOnInit(): void {
    forkJoin({
      customers: this.customerService.getCustomers(),
      summary: this.analyticsService.getSummary(),
      monthly: this.analyticsService.getMonthlyTrend(6)
    }).subscribe({
      next: ({ customers, summary, monthly }) => {
        this.customers = customers;
        this.monthlyTrend = monthly;
        this.kpis = this.toKpis(summary);
        this.loading = false;
      },
      error: () => {
        this.apiAvailable = false;
        this.loading = false;
      }
    });
  }

  barHeight(revenue: number): number {
    const max = Math.max(...this.monthlyTrend.map(metric => Number(metric.revenue)), 1);
    return Math.max(8, Math.round((Number(revenue) / max) * 100));
  }

  private toKpis(summary: AnalyticsSummary): Kpi[] {
    return [
      { label: 'Portfolio Revenue', value: this.currency(summary.revenue), detail: 'Selected reporting period' },
      { label: 'Net Income', value: this.currency(summary.netIncome), detail: `${Number(summary.operatingMarginPercent).toFixed(1)}% operating margin` },
      { label: 'Active Customers', value: String(summary.activeCustomers), detail: `${summary.totalCustomers} total customers` },
      { label: 'Operating Expenses', value: this.currency(summary.expenses), detail: 'Recorded expense transactions' }
    ];
  }

  private currency(value: number): string {
    return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(Number(value));
  }
}
