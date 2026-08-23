export interface AnalyticsSummary {
  revenue: number;
  expenses: number;
  netIncome: number;
  operatingMarginPercent: number;
  totalCustomers: number;
  activeCustomers: number;
}

export interface MonthlyMetric {
  month: string;
  revenue: number;
  expenses: number;
  netIncome: number;
}
