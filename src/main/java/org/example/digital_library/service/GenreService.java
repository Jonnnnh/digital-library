package org.example.digital_library.service;

import org.example.digital_library.model.Genre;
import org.example.digital_library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public void createGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public Genre getGenreById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    public List<Object[]> findMostPopularGenres() {
        return genreRepository.findMostPopularGenres();
    }
}
