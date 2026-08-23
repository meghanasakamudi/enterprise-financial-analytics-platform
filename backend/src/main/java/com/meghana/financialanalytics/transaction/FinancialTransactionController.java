package com.meghana.financialanalytics.transaction;

import com.meghana.financialanalytics.customer.Customer;
import com.meghana.financialanalytics.customer.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class FinancialTransactionController {
    private final FinancialTransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public FinancialTransactionController(FinancialTransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<TransactionResponse> findAll(@RequestParam(required = false) Long customerId) {
        List<FinancialTransaction> transactions = customerId == null
                ? transactionRepository.findAll()
                : transactionRepository.findByCustomerIdOrderByTransactionDateDesc(customerId);
        return transactions.stream().map(TransactionResponse::from).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(@Valid @RequestBody TransactionRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setCustomer(customer);
        transaction.setTransactionDate(request.transactionDate());
        transaction.setCategory(request.category());
        transaction.setAmount(request.amount());
        transaction.setTransactionType(request.transactionType());
        return TransactionResponse.from(transactionRepository.save(transaction));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        FinancialTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
        transactionRepository.delete(transaction);
    }
}
