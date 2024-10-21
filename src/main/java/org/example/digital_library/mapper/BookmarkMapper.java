package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.BookmarkDto;
import org.example.digital_library.model.dto.UserDto;
import org.example.digital_library.model.entity.BookmarkEntity;
import org.example.digital_library.model.entity.BookEntity;
import org.example.digital_library.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkDto toDto(BookmarkEntity bookmarkEntity) {
        if (bookmarkEntity == null) {
            return null;
        }

        BookmarkDto bookmarkDto = new BookmarkDto();
        bookmarkDto.setId(bookmarkEntity.getId());
        bookmarkDto.setPageNumber(bookmarkEntity.getPageNumber());
        bookmarkDto.setCreateAt(bookmarkEntity.getCreatedAt());

        if (bookmarkEntity.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(bookmarkEntity.getUser().getId());
            userDto.setUsername(bookmarkEntity.getUser().getUsername());
            userDto.setEmail(bookmarkEntity.getUser().getEmail());
            bookmarkDto.setUser(userDto);
        }

        if (bookmarkEntity.getBook() != null) {
            bookmarkDto.setBookId(bookmarkEntity.getBook().getId());
        }

        if (bookmarkEntity.getBook() != null) {
            bookmarkDto.setBookId(bookmarkEntity.getBook().getId());
            bookmarkDto.setBookTitle(bookmarkEntity.getBook().getTitle());
        }

        return bookmarkDto;
    }

    public BookmarkEntity toEntity(BookmarkDto bookmarkDto) {
        if (bookmarkDto == null) {
            return null;
        }

        BookmarkEntity bookmarkEntity = new BookmarkEntity();
        bookmarkEntity.setId(bookmarkDto.getId());
        bookmarkEntity.setPageNumber(bookmarkDto.getPageNumber());
        bookmarkEntity.setCreatedAt(bookmarkDto.getCreateAt());

        if (bookmarkDto.getUser() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(bookmarkDto.getUser().getId());
            userEntity.setUsername(bookmarkDto.getUser().getUsername());
            userEntity.setEmail(bookmarkDto.getUser().getEmail());
            bookmarkEntity.setUser(userEntity);
        }

        if (bookmarkDto.getBookId() != null) {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setId(bookmarkDto.getBookId());
            bookmarkEntity.setBook(bookEntity);
        }

        return bookmarkEntity;
    }
}
