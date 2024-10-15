package org.example.digital_library.service;

import lombok.AllArgsConstructor;
import org.example.digital_library.mapper.BookmarkMapper;
import org.example.digital_library.model.dto.BookmarkDto;
import org.example.digital_library.model.entity.BookmarkEntity;
import org.example.digital_library.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookmarkService {

    private BookmarkRepository bookmarkRepository;

    private final BookmarkMapper bookmarkMapper = BookmarkMapper.INSTANCE;

    public void addBookmark(BookmarkDto bookmarkDto) {
        BookmarkEntity bookmarkEntity = bookmarkMapper.toEntity(bookmarkDto);
        bookmarkRepository.save(bookmarkEntity);
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
