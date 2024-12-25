package org.example.mapper;

import org.example.model.dto.AuthorDto;
import org.example.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    AuthorDto toDto(Author authorEntity);
    Author toEntity(AuthorDto authorDto);
}

