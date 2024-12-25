package org.example.repository;

import org.example.model.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserId(Long userId);
    List<Bookmark> findByBookIdAndUserId(Long bookId, Long userId);
}
