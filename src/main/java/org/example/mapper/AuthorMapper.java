package org.example.mapper;

import org.example.model.dto.AuthorDto;
import org.example.model.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toDto(AuthorEntity authorEntity);

    AuthorEntity toEntity(AuthorDto authorDto);
}

