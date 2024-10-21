package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.AuthorDto;
import org.example.digital_library.model.entity.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto toDto(AuthorEntity authorEntity) {
        if (authorEntity == null) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorEntity.getId());
        authorDto.setFirstName(authorEntity.getFirstName());
        authorDto.setLastName(authorEntity.getLastName());
        authorDto.setBirthDate(authorEntity.getBirthDate());

        return authorDto;
    }

    public AuthorEntity toEntity(AuthorDto authorDto) {
        if (authorDto == null) {
            return null;
        }

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorDto.getId());
        authorEntity.setFirstName(authorDto.getFirstName());
        authorEntity.setLastName(authorDto.getLastName());
        authorEntity.setBirthDate(authorDto.getBirthDate());

        return authorEntity;
    }
}

