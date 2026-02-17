package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false, unique = true) private String email;
    private LocalDate subscriptionExpiry;
    @Column(nullable = false) private String status;
    public Long getId(){return id;} public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public LocalDate getSubscriptionExpiry(){return subscriptionExpiry;} public void setSubscriptionExpiry(LocalDate subscriptionExpiry){this.subscriptionExpiry=subscriptionExpiry;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
