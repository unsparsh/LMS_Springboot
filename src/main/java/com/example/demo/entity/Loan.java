package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @Column(nullable = false, unique = true) private String requestId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    @Column(nullable = false) private String status;
    public Long getId(){return id;} public Member getMember(){return member;} public void setMember(Member member){this.member=member;}
    public Book getBook(){return book;} public void setBook(Book book){this.book=book;}
    public String getRequestId(){return requestId;} public void setRequestId(String requestId){this.requestId=requestId;}
    public LocalDate getBorrowDate(){return borrowDate;} public void setBorrowDate(LocalDate borrowDate){this.borrowDate=borrowDate;}
    public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate dueDate){this.dueDate=dueDate;}
    public LocalDate getReturnDate(){return returnDate;} public void setReturnDate(LocalDate returnDate){this.returnDate=returnDate;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
