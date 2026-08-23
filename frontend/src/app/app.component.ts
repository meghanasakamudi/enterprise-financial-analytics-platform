import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerService } from './services/customer.service';
import { Customer } from './models/customer';

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
  loading = true;
  apiAvailable = true;

  readonly kpis: Kpi[] = [
    { label: 'Portfolio Revenue', value: '$1.24M', detail: '+8.4% vs prior period' },
    { label: 'Operating Margin', value: '24.8%', detail: '+1.6 pts vs prior period' },
    { label: 'Active Customers', value: '1,842', detail: '92% retention' },
    { label: 'Risk Alerts', value: '17', detail: '5 require review' }
  ];

  constructor(private readonly customerService: CustomerService) {}

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe({
      next: customers => { this.customers = customers; this.loading = false; },
      error: () => { this.apiAvailable = false; this.loading = false; }
    });
  }
}
