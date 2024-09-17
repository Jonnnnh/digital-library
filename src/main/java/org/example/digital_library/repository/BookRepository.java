package org.example.digital_library.repository;

import org.example.digital_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR b.title LIKE %:title%) AND " +
            "(:authorId IS NULL OR b.author.id = :authorId) AND " +
            "(:genreId IS NULL OR b.genre.id = :genreId)")
    List<Book> findBySearchCriteria(@Param("title") String title,
                                    @Param("authorId") Long authorId,
                                    @Param("genreId") Long genreId);

    @Query("SELECT COUNT(b) FROM Book b")
    long countBooks();
}
