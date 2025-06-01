package com.payment.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class TransactionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }
}

@Entity
class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long paymentId;
    private String status;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

interface TransactionRepository extends JpaRepository<Transaction, Long> {}

@RestController
@RequestMapping("/transactions")
class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return repository.save(transaction);
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }
}
