package com.meghana.financialanalytics.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Customer> findAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody Customer customer) { return repository.save(customer); }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @Valid @RequestBody Customer input) {
        Customer customer = findById(id);
        customer.setName(input.getName()); customer.setEmail(input.getEmail());
        customer.setSegment(input.getSegment()); customer.setStatus(input.getStatus());
        return repository.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repository.delete(findById(id)); }
}
