package com.meghana.financialanalytics.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
    List<FinancialTransaction> findByCustomerIdOrderByTransactionDateDesc(Long customerId);

    @Query("select coalesce(sum(t.amount), 0) from FinancialTransaction t where upper(t.transactionType) = upper(:type)")
    BigDecimal totalByType(@Param("type") String type);

    @Query("select coalesce(sum(t.amount), 0) from FinancialTransaction t where upper(t.transactionType) = upper(:type) and t.transactionDate between :start and :end")
    BigDecimal totalByTypeAndDateRange(@Param("type") String type, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("select count(distinct t.customer.id) from FinancialTransaction t where t.transactionDate between :start and :end")
    long activeCustomers(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
