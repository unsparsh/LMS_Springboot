package com.example.demo.repository;
import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
	List<Book> findByAuthorContainingIgnoreCase(String author);

}
