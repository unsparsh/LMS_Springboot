package com.example.demo.service;

import com.example.demo.dto.BookDtos;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<BookDtos.BookResponse> list(String author) {
        List<Book> books = (author == null || author.isBlank()) ? bookRepository.findAll() : bookRepository.findByAuthorContainingIgnoreCase(author);
        return books.stream().map(b -> new BookDtos.BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.getCategory()==null?null:b.getCategory().getId(), b.getCopiesTotal(), b.getCopiesAvailable())).toList();
    }

    @Transactional
    public BookDtos.BookResponse create(BookDtos.BookRequest req) {
        Book b = new Book();
        b.setTitle(req.title()); b.setAuthor(req.author()); b.setIsbn(req.isbn());
        b.setCopiesTotal(req.copiesTotal()); b.setCopiesAvailable(req.copiesAvailable());
        if (req.categoryId() != null) b.setCategory(categoryRepository.findById(req.categoryId()).orElseThrow());
        b = bookRepository.save(b);
        return new BookDtos.BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.getCategory()==null?null:b.getCategory().getId(), b.getCopiesTotal(), b.getCopiesAvailable());
    }
}
