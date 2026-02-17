package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "fine")
public class Fine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;
    @Column(nullable = false) private BigDecimal amount;
    @Column(nullable = false) private String reason;
    @Column(nullable = false) private boolean paidFlag;
    @Column(nullable = false) private OffsetDateTime createdAt;
    public Long getId(){return id;} public Loan getLoan(){return loan;} public void setLoan(Loan loan){this.loan=loan;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal amount){this.amount=amount;}
    public String getReason(){return reason;} public void setReason(String reason){this.reason=reason;}
    public boolean isPaidFlag(){return paidFlag;} public void setPaidFlag(boolean paidFlag){this.paidFlag=paidFlag;}
    public OffsetDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(OffsetDateTime createdAt){this.createdAt=createdAt;}
}
