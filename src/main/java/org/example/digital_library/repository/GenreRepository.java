package org.example.digital_library.repository;

import org.example.digital_library.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    @Query("SELECT g.name FROM GenreEntity g JOIN BookEntity b ON b.genre.id = g.id GROUP BY g.name ORDER BY COUNT(b) DESC")
    List<String> findMostPopularGenre();
}
