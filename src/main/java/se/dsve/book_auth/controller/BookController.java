package se.dsve.book_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dsve.book_auth.dtos.BookDto;
import se.dsve.book_auth.exceptions.ResourceNotFoundException;
import se.dsve.book_auth.model.Book;
import se.dsve.book_auth.services.BookService;

import java.util.List;

/**
 * The BookController class is responsible for handling HTTP requests related to books.
 * It provides methods for retrieving, adding, updating, and deleting books.
 * Business logic is handled by the BookService class.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        // TODO: Write your code here;
        return bookService.getAllBooks();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        // TODO: Write your code here
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public Book addBook(@RequestBody BookDto bookDto) {
        // TODO: Write your code here
        return bookService.addBook(bookDto);
    }

    @PostMapping("/addPredefinedBooks")
    public ResponseEntity<Void> addPredefinedBooks() {
        bookService.addPredefinedBooks();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(updatedBook);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // TODO: Write your code here
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
