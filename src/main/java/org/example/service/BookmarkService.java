package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.BookmarkMapper;
import org.example.model.dto.BookmarkDto;
import org.example.model.entity.Bookmark;
import org.example.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class BookmarkService {

    private BookmarkRepository bookmarkRepository;
    private BookmarkMapper bookmarkMapper;


    public void addBookmark(BookmarkDto bookmarkDto) {
        log.info("Adding bookmark with Book ID: {}, User ID: {}, Page Number: {}",
                bookmarkDto.getBookId(),
                bookmarkDto.getUser().getId(),
                bookmarkDto.getPageNumber());
        Bookmark bookmarkEntity = bookmarkMapper.toEntity(bookmarkDto);
        if (bookmarkEntity.getUser() == null) {
            log.error("User is null. Cannot save bookmark.");
            throw new RuntimeException("User cannot be null");
        }
        bookmarkRepository.save(bookmarkEntity);
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
