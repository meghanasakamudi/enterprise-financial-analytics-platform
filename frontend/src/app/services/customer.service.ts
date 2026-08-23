import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({ providedIn: 'root' })
export class CustomerService {
  private readonly apiUrl = 'http://localhost:8080/api/customers';
  constructor(private readonly http: HttpClient) {}
  getCustomers(): Observable<Customer[]> { return this.http.get<Customer[]>(this.apiUrl); }
}
