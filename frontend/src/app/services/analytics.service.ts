import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AnalyticsSummary, MonthlyMetric } from '../models/analytics';

@Injectable({ providedIn: 'root' })
export class AnalyticsService {
  private readonly apiUrl = 'http://localhost:8080/api/analytics';
  constructor(private readonly http: HttpClient) {}
  getSummary(): Observable<AnalyticsSummary> { return this.http.get<AnalyticsSummary>(`${this.apiUrl}/summary`); }
  getMonthlyTrend(months = 6): Observable<MonthlyMetric[]> { return this.http.get<MonthlyMetric[]>(`${this.apiUrl}/monthly?months=${months}`); }
}
