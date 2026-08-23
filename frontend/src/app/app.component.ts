import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';
import { CustomerService } from './services/customer.service';
import { AnalyticsService } from './services/analytics.service';
import { TransactionService } from './services/transaction.service';
import { Customer } from './models/customer';
import { AnalyticsSummary, MonthlyMetric } from './models/analytics';
import { FinancialTransaction, TransactionRequest } from './models/transaction';

interface Kpi { label: string; value: string; detail: string; }

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  customers: Customer[] = [];
  transactions: FinancialTransaction[] = [];
  monthlyTrend: MonthlyMetric[] = [];
  kpis: Kpi[] = [];
  loading = true;
  apiAvailable = true;
  searchTerm = '';
  customerForm: Customer = { name: '', email: '', segment: 'Growth', status: 'Active' };
  transactionForm: TransactionRequest = { customerId: 0, transactionDate: new Date().toISOString().slice(0, 10), category: 'Services', amount: 0, transactionType: 'REVENUE' };
  message = '';

  constructor(
    private readonly customerService: CustomerService,
    private readonly analyticsService: AnalyticsService,
    private readonly transactionService: TransactionService
  ) {}

  ngOnInit(): void { this.refreshDashboard(); }

  get filteredCustomers(): Customer[] {
    const term = this.searchTerm.trim().toLowerCase();
    if (!term) return this.customers;
    return this.customers.filter(customer => [customer.name, customer.email, customer.segment, customer.status].some(value => value?.toLowerCase().includes(term)));
  }

  refreshDashboard(): void {
    this.loading = true;
    forkJoin({
      customers: this.customerService.getCustomers(),
      transactions: this.transactionService.getTransactions(),
      summary: this.analyticsService.getSummary(),
      monthly: this.analyticsService.getMonthlyTrend(6)
    }).subscribe({
      next: ({ customers, transactions, summary, monthly }) => {
        this.customers = customers;
        this.transactions = transactions;
        this.monthlyTrend = monthly;
        this.kpis = this.toKpis(summary);
        if (!this.transactionForm.customerId && customers[0]?.id) this.transactionForm.customerId = customers[0].id;
        this.apiAvailable = true;
        this.loading = false;
      },
      error: () => { this.apiAvailable = false; this.loading = false; }
    });
  }

  addCustomer(): void {
    this.message = '';
    this.customerService.createCustomer(this.customerForm).subscribe({
      next: () => {
        this.customerForm = { name: '', email: '', segment: 'Growth', status: 'Active' };
        this.message = 'Customer created successfully.';
        this.refreshDashboard();
      },
      error: () => this.message = 'Unable to create customer. Check the form and backend connection.'
    });
  }

  addTransaction(): void {
    this.message = '';
    this.transactionService.createTransaction(this.transactionForm).subscribe({
      next: () => {
        this.transactionForm = { ...this.transactionForm, amount: 0, category: 'Services', transactionDate: new Date().toISOString().slice(0, 10) };
        this.message = 'Transaction recorded and analytics refreshed.';
        this.refreshDashboard();
      },
      error: () => this.message = 'Unable to record transaction. Select a customer and enter a positive amount.'
    });
  }

  barHeight(revenue: number): number {
    const max = Math.max(...this.monthlyTrend.map(metric => Number(metric.revenue)), 1);
    return Math.max(8, Math.round((Number(revenue) / max) * 100));
  }

  formatCurrency(value: number): string { return this.currency(value); }

  private toKpis(summary: AnalyticsSummary): Kpi[] {
    return [
      { label: 'Portfolio Revenue', value: this.currency(summary.revenue), detail: 'Calculated from revenue transactions' },
      { label: 'Net Income', value: this.currency(summary.netIncome), detail: `${Number(summary.operatingMarginPercent).toFixed(1)}% operating margin` },
      { label: 'Active Customers', value: String(summary.activeCustomers), detail: `${summary.totalCustomers} total customers` },
      { label: 'Operating Expenses', value: this.currency(summary.expenses), detail: 'Calculated from expense transactions' }
    ];
  }

  private currency(value: number): string {
    return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(Number(value));
  }
}
