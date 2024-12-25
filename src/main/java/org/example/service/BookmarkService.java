package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.BookmarkMapper;
import org.example.model.dto.BookmarkDto;
import org.example.model.entity.Book;
import org.example.model.entity.Bookmark;
import org.example.model.entity.User;
import org.example.repository.BookRepository;
import org.example.repository.BookmarkRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    @Transactional
    public void addBookmark(BookmarkDto bookmarkDto) {
        log.info("Adding bookmark with Book ID: {}, User ID: {}, Page Number: {}",
                bookmarkDto.getBookId(),
                bookmarkDto.getUser().getId(),
                bookmarkDto.getPageNumber());

        Bookmark bookmarkEntity = bookmarkMapper.toEntity(bookmarkDto);

        Book book = bookRepository.findById(bookmarkDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookmarkDto.getBookId()));

        User user = userRepository.findById(bookmarkDto.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + bookmarkDto.getUser().getId()));

        bookmarkEntity.setBook(book);
        bookmarkEntity.setUser(user);

        if (bookmarkEntity.getCreatedAt() == null) {
            bookmarkEntity.setCreatedAt(LocalDateTime.now());
        }
        bookmarkRepository.save(bookmarkEntity);
        log.info("Bookmark saved with ID: {}", bookmarkEntity.getId());
    }

    public List<BookmarkDto> getUserBookmarksForBook(Long bookId, Long userId) {
        return bookmarkRepository.findByBookIdAndUserId(bookId, userId).stream()
                .map(bookmarkMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookmarkDto> getUserBookmarks(Long userId) {
        return bookmarkRepository.findByUserId(userId).stream()
                .map(bookmarkMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteBookmark(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }
}
