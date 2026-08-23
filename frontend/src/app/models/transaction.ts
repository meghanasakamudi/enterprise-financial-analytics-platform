export interface FinancialTransaction {
  id: number;
  customerId: number;
  customerName: string;
  transactionDate: string;
  category: string;
  amount: number;
  transactionType: string;
}

export interface TransactionRequest {
  customerId: number;
  transactionDate: string;
  category: string;
  amount: number;
  transactionType: string;
}
