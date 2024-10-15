package org.example.digital_library.service;

import lombok.AllArgsConstructor;
import org.example.digital_library.repository.BookRepository;
import org.example.digital_library.repository.GenreRepository;
import org.example.digital_library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;

    public long getTotalBooks() {
        return bookRepository.count();
    }

    public String getMostPopularGenre() {
        List<String> popularGenres = genreRepository.findMostPopularGenre();
        return popularGenres.isEmpty() ? "No genres available" : popularGenres.get(0);
    }

    public String getMostActiveUser() {
        return userRepository.findMostActiveUser();
    }
}
