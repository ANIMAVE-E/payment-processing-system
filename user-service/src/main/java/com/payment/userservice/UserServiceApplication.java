package com.payment.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

interface UserRepository extends JpaRepository<User, Long> {}

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
