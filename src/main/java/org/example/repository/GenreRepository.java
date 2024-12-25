package org.example.repository;

import org.example.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g.name FROM Genre g JOIN Book b ON b.genre.id = g.id GROUP BY g.name ORDER BY COUNT(b) DESC")
    List<String> findMostPopularGenre();
}
