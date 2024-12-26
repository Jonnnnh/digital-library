package org.example.service;

import lombok.AllArgsConstructor;
import org.example.repository.BookRepository;
import org.example.repository.GenreRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Controller
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
        List<String> mostActiveUsers = userRepository.findMostActiveUsers();
        return mostActiveUsers.isEmpty() ? "No active users" : mostActiveUsers.get(0);
    }
}