package se.dsve.book_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.dsve.book_auth.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Here you can add custom methods for interacting with the Book entity in the database, if needed.
}
