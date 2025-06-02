package com.payment.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;  // ✅ updated from javax.persistence
import java.util.List;

@SpringBootApplication
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}

@Entity
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Double amount;
    private String currency;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}

interface PaymentRepository extends JpaRepository<Payment, Long> {}

@RestController
@RequestMapping("/payments")
class PaymentController {

    @Autowired
    private PaymentRepository repository;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return repository.save(payment);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }
}
