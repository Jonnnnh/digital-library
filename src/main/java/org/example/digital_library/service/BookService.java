package org.example.digital_library.service;

import lombok.AllArgsConstructor;
import org.example.digital_library.mapper.BookMapper;
import org.example.digital_library.model.domain.Book;
import org.example.digital_library.model.dto.BookDto;
import org.example.digital_library.model.entity.AuthorEntity;
import org.example.digital_library.model.entity.BookEntity;
import org.example.digital_library.model.entity.GenreEntity;
import org.example.digital_library.repository.BookRepository;
import org.example.digital_library.repository.AuthorRepository;
import org.example.digital_library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    public List<BookDto> getAllBooks(String title, Long authorId, Long genreId) {
        List<BookEntity> books = bookRepository.findBooks(title, authorId, genreId);
        return books.stream()
                .map(BookMapper.INSTANCE::bookEntityToBookDto)
                .collect(Collectors.toList());
    }


    public BookDto getBookById(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return BookMapper.INSTANCE.bookEntityToBookDto(bookEntity);
    }

    public void save(BookDto bookDto) {
        GenreEntity genreEntity = genreRepository.findById(bookDto.getGenre().getId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        AuthorEntity authorEntity = authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        BookEntity bookEntity = BookMapper.INSTANCE.bookDtoToBookEntity(bookDto);
        bookEntity.setGenre(genreEntity);
        bookEntity.setAuthor(authorEntity);

        String filePath = "/files/book_" + (bookRepository.count() + 1) + ".pdf";
        bookEntity.setFilePath(filePath);

        bookRepository.save(bookEntity);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
