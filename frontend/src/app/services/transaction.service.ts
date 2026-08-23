import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FinancialTransaction, TransactionRequest } from '../models/transaction';

@Injectable({ providedIn: 'root' })
export class TransactionService {
  private readonly apiUrl = 'http://localhost:8080/api/transactions';
  constructor(private readonly http: HttpClient) {}

  getTransactions(): Observable<FinancialTransaction[]> {
    return this.http.get<FinancialTransaction[]>(this.apiUrl);
  }

  createTransaction(request: TransactionRequest): Observable<FinancialTransaction> {
    return this.http.post<FinancialTransaction>(this.apiUrl, request);
  }
}
