package se.dsve.book_auth.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dsve.book_auth.dtos.BookDto;
import se.dsve.book_auth.exceptions.ResourceNotFoundException;
import se.dsve.book_auth.model.Book;
import se.dsve.book_auth.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * The BookService class is responsible for handling business logic related to books.
 * It provides methods for retrieving, adding, updating, and deleting books.
 * Data access is handled by the BookRepository class.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        // TODO: Write your code here
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        // TODO: Write your code here
        return bookRepository.findById(id);
    }

    public Book addBook(BookDto bookDto) {
        // TODO: Write your code here
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDto bookDto) {
        // TODO: Write your code here
        Book book = getBookOrFail(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        return bookRepository.save(book);
    }


    public void deleteBook(Long id) {
        // TODO: Write your code here
        bookRepository.deleteById(id);
    }

    private Book getBookOrFail(Long id) {
        // TODO: Write your code here
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }
    @PostConstruct
    public void init() {
        addPredefinedBooks();
    }

    public void addPredefinedBooks() {
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));
            bookRepository.save(new Book(null, "To Kill a Mockingbird", "Harper Lee", "9780061120084"));
            bookRepository.save(new Book(null, "1984", "George Orwell", "9780451524935"));
        }
    }
}
