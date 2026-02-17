package com.example.demo.service;

import com.example.demo.dto.BookDtos;
<<<<<<< HEAD
=======
import com.example.demo.dto.BookDtos.BookResponse;
>>>>>>> main
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

=======
>>>>>>> main
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<BookDtos.BookResponse> list(String author, Long categoryId, Boolean availableOnly) {
        Specification<Book> spec = Specification.where(null);

        if (author != null && !author.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
        }
        if (categoryId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId));
        }
        if (Boolean.TRUE.equals(availableOnly)) {
            spec = spec.and((root, query, cb) -> cb.greaterThan(root.get("copiesAvailable"), 0));
        }

        return bookRepository.findAll(spec).stream().map(this::toResponse).toList();
    }
<<<<<<< HEAD
=======
    public List<BookDtos.BookResponse> list(String author) {
        List<Book> books = (author == null || author.isBlank()) ? bookRepository.findAll() : bookRepository.findByAuthorContainingIgnoreCase(author);
        return books.stream().map(b -> new BookDtos.BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.getCategory()==null?null:b.getCategory().getId(), b.getCopiesTotal(), b.getCopiesAvailable())).toList();
    }
>>>>>>> main

    @Transactional
    public BookDtos.BookResponse create(BookDtos.BookRequest req) {
        Book book = new Book();
        apply(book, req);
        return toResponse(bookRepository.save(book));
    }

    @Transactional
    public BookDtos.BookResponse update(Long id, BookDtos.BookRequest req) {
        Book book = bookRepository.findById(id).orElseThrow();
        apply(book, req);
        return toResponse(bookRepository.save(book));
    }

    @Transactional
    public BookDtos.BookResponse patch(Long id, BookDtos.BookPatchRequest req) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (req.title() != null) book.setTitle(req.title());
        if (req.author() != null) book.setAuthor(req.author());
        if (req.isbn() != null) book.setIsbn(req.isbn());
        if (req.categoryId() != null) book.setCategory(categoryRepository.findById(req.categoryId()).orElseThrow());
        if (req.copiesTotal() != null) book.setCopiesTotal(req.copiesTotal());
        if (req.copiesAvailable() != null) book.setCopiesAvailable(req.copiesAvailable());
        return toResponse(bookRepository.save(book));
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private void apply(Book book, BookDtos.BookRequest req) {
        book.setTitle(req.title());
        book.setAuthor(req.author());
        book.setIsbn(req.isbn());
        book.setCopiesTotal(req.copiesTotal());
        book.setCopiesAvailable(req.copiesAvailable());
        if (req.categoryId() != null) {
            book.setCategory(categoryRepository.findById(req.categoryId()).orElseThrow());
        } else {
            book.setCategory(null);
        }
    }

    private BookDtos.BookResponse toResponse(Book b) {
        return new BookDtos.BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(),
                b.getCategory() == null ? null : b.getCategory().getId(), b.getCopiesTotal(), b.getCopiesAvailable());
    }
}
