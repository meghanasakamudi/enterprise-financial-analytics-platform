package com.meghana.financialanalytics.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @Email @NotBlank @Column(unique = true) private String email;
    private String segment;
    private String status;

    public Customer() {}
    public Customer(String name, String email, String segment, String status) {
        this.name = name; this.email = email; this.segment = segment; this.status = status;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSegment() { return segment; }
    public void setSegment(String segment) { this.segment = segment; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
