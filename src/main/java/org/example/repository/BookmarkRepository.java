package org.example.repository;

import org.example.model.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    List<BookmarkEntity> findByUserId(Long userId);
    List<BookmarkEntity> findByBookIdAndUserId(Long bookId, Long userId);
}
