package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.BookMapper;
import org.example.model.dto.BookDto;
import org.example.model.entity.Author;
import org.example.model.entity.Book;
import org.example.model.entity.Genre;
import org.example.repository.BookRepository;
import org.example.repository.AuthorRepository;
import org.example.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    public List<BookDto> getAllBooks(String title, Long authorId, Long genreId) {
        List<Book> books = bookRepository.findBooks(title, authorId, genreId);
        books.forEach(book -> {
//            if (book.getAuthor() != null) {
//                log.info("Book Author Entity: {}", book.getAuthor());
//                log.info("Bookk: {}, Author: {} {}", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName());
//            } else {
//                log.warn("Bookk: {} has no author", book.getTitle());
//            }
        });
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }



    public BookDto getBookById(Long id) {
        Book bookEntity = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
//        log.info("Book Author Entity: {}", bookEntity.getAuthor());
        return bookMapper.toDto(bookEntity);
    }

    public void save(BookDto bookDto) {
        Genre genreEntity = genreRepository.findById(bookDto.getGenre().getId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        Author authorEntity = authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book bookEntity;

        if (bookDto.getId() != null) {
            bookEntity = bookRepository.findById(bookDto.getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
        } else {
            bookEntity = new Book();

            String filePath = "/files/book_" + (bookRepository.count() + 1) + ".pdf";
            bookEntity.setFilePath(filePath);

            bookEntity.setPublishedDate(bookDto.getPublishedDate() != null ? bookDto.getPublishedDate() : LocalDate.now());
        }
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setDescription(bookDto.getDescription());
        bookEntity.setGenre(genreEntity);
        bookEntity.setAuthor(authorEntity);
        bookRepository.save(bookEntity);
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
