package org.example.digital_library.service;

import lombok.AllArgsConstructor;
import org.example.digital_library.mapper.AuthorMapper;
import org.example.digital_library.model.dto.AuthorDto;
import org.example.digital_library.model.entity.AuthorEntity;
import org.example.digital_library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return authorMapper.toDto(entity);
    }

    public void createAuthor(AuthorDto authorDto) {
        AuthorEntity entity = authorMapper.toEntity(authorDto);
        authorRepository.save(entity);
    }
}
