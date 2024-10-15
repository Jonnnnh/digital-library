package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.AuthorDto;
import org.example.digital_library.model.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toDto(AuthorEntity authorEntity);

    AuthorEntity toEntity(AuthorDto authorDto);
}

