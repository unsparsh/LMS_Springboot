package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    @Column(nullable = false) private String author;
    @Column(nullable = false, unique = true) private String isbn;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category category;
    @Column(nullable = false) private Integer copiesTotal;
    @Column(nullable = false) private Integer copiesAvailable;
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getAuthor(){return author;} public void setAuthor(String author){this.author=author;}
    public String getIsbn(){return isbn;} public void setIsbn(String isbn){this.isbn=isbn;}
    public Category getCategory(){return category;} public void setCategory(Category category){this.category=category;}
    public Integer getCopiesTotal(){return copiesTotal;} public void setCopiesTotal(Integer copiesTotal){this.copiesTotal=copiesTotal;}
    public Integer getCopiesAvailable(){return copiesAvailable;} public void setCopiesAvailable(Integer copiesAvailable){this.copiesAvailable=copiesAvailable;}
}
