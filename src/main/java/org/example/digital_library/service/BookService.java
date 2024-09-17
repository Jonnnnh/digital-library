package org.example.digital_library.service;

import org.example.digital_library.model.Book;
import org.example.digital_library.model.BookSpecification;
import org.example.digital_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksSorted(String sortField) {
        return bookRepository.findAll(Sort.by(sortField));
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void save(Book book) {
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            System.out.println("Error while saving book: " + e.getMessage());
            throw e;
        }
    }

    public long countBooks() {
        return bookRepository.countBooks();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = getBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setGenre(updatedBook.getGenre());
        book.setFilePath(updatedBook.getFilePath());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String title, Long authorId, Long genreId) {
        if (title == null && authorId == null && genreId == null) {
            return bookRepository.findAll();
        }
        return bookRepository.findBySearchCriteria(title, authorId, genreId);
    }
}

