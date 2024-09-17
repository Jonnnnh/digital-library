package org.example.digital_library.repository;

import org.example.digital_library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g.name, COUNT(b) as bookCount FROM Genre g JOIN Book b ON b.genre.id = g.id GROUP BY g.name ORDER BY bookCount DESC")
    List<Object[]> findMostPopularGenres();

    @Query("SELECT g.name FROM Genre g JOIN Book b ON b.genre.id = g.id GROUP BY g.name ORDER BY COUNT(b) DESC")
    List<String> findMostPopularGenre();
}


