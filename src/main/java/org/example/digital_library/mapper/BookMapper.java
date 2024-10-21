package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.AuthorDto;
import org.example.digital_library.model.dto.BookDto;
import org.example.digital_library.model.dto.GenreDto;
import org.example.digital_library.model.entity.AuthorEntity;
import org.example.digital_library.model.entity.BookEntity;
import org.example.digital_library.model.entity.GenreEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookMapper {

    public BookDto toDto(BookEntity bookEntity) {
        if (bookEntity == null) {
            return null;
        }

        log.info("Mapping BookEntity to BookDto: Title = {}, Author = {}",
                bookEntity.getTitle(),
                bookEntity.getAuthor() != null ? bookEntity.getAuthor().getFirstName() + " " + bookEntity.getAuthor().getLastName() : "null");

        BookDto bookDto = new BookDto();
        bookDto.setId(bookEntity.getId());
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setDescription(bookEntity.getDescription());
        bookDto.setFilePath(bookEntity.getFilePath());
        bookDto.setPublishedDate(bookEntity.getPublishedDate());

        if (bookEntity.getAuthor() != null) {
            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(bookEntity.getAuthor().getId());
            authorDto.setFirstName(bookEntity.getAuthor().getFirstName());
            authorDto.setLastName(bookEntity.getAuthor().getLastName());
            authorDto.setBirthDate(bookEntity.getAuthor().getBirthDate());
            bookDto.setAuthor(authorDto);
        }

        if (bookEntity.getGenre() != null) {
            GenreDto genreDto = new GenreDto();
            genreDto.setId(bookEntity.getGenre().getId());
            genreDto.setName(bookEntity.getGenre().getName());
            bookDto.setGenre(genreDto);
        }

        return bookDto;
    }

    public BookEntity toEntity(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }

        log.info("Mapping BookDto to BookEntity: Title = {}, Author = {}",
                bookDto.getTitle(),
                bookDto.getAuthor() != null ? bookDto.getAuthor().getFirstName() + " " + bookDto.getAuthor().getLastName() : "null");

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDto.getId());
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setDescription(bookDto.getDescription());
        bookEntity.setFilePath(bookDto.getFilePath());
        bookEntity.setPublishedDate(bookDto.getPublishedDate());

        if (bookDto.getAuthor() != null) {
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setId(bookDto.getAuthor().getId());
            authorEntity.setFirstName(bookDto.getAuthor().getFirstName());
            authorEntity.setLastName(bookDto.getAuthor().getLastName());
            authorEntity.setBirthDate(bookDto.getAuthor().getBirthDate());
            bookEntity.setAuthor(authorEntity);
        }

        if (bookDto.getGenre() != null) {
            GenreEntity genreEntity = new GenreEntity();
            genreEntity.setId(bookDto.getGenre().getId());
            genreEntity.setName(bookDto.getGenre().getName());
            bookEntity.setGenre(genreEntity);
        }

        return bookEntity;
    }
}
