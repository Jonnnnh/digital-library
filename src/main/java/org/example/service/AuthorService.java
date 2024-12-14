package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.AuthorMapper;
import org.example.model.dto.AuthorDto;
import org.example.model.entity.AuthorEntity;
import org.example.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorEntities.stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }


    public AuthorDto getAuthorById(Long id) {
        AuthorEntity entity = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        log.info("AuthorEntity details: {} {}", entity.getFirstName(), entity.getLastName());
        return authorMapper.toDto(entity);
    }

    public void createAuthor(AuthorDto authorDto) {
        AuthorEntity entity = authorMapper.toEntity(authorDto);
        authorRepository.save(entity);
    }
}
