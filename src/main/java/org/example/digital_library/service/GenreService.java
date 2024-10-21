package org.example.digital_library.service;

import lombok.extern.slf4j.Slf4j;
import org.example.digital_library.mapper.GenreMapper;
import org.example.digital_library.model.dto.GenreDto;
import org.example.digital_library.model.domain.Genre;
import org.example.digital_library.model.entity.GenreEntity;
import org.example.digital_library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    private final GenreMapper genreMapper = GenreMapper.INSTANCE;

    public List<GenreDto> getAllGenres() {
        List<GenreEntity> genres = genreRepository.findAll();
        log.info("Genres fetched: {}", genres);
        if (genres == null || genres.isEmpty()) {
            log.warn("No genres found in the database.");
        } else {
            genres.forEach(genre -> log.info("Loaded Genre from DB - ID: {}, Name: {}", genre.getId(), genre.getName()));
        }
        return genres.stream()
                .map(genre -> new GenreDto(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }

    public GenreDto getGenreById(Long id) {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        return new GenreDto(genre.getId(), genre.getName());
    }


    public void createGenre(GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.toEntity(genreDto);
        genreRepository.save(genreEntity);
    }
}
