package org.example.digital_library.repository;

import org.example.digital_library.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
    @Query("SELECT b FROM BookEntity b WHERE " +
            "(:title IS NULL OR b.title LIKE %:title%) AND " +
            "(:authorId IS NULL OR b.author.id = :authorId) AND " +
            "(:genreId IS NULL OR b.genre.id = :genreId)")
    List<BookEntity> findBooks(@Param("title") String title,
                               @Param("authorId") Long authorId,
                               @Param("genreId") Long genreId);
}
