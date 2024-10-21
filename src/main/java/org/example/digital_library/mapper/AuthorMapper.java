package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.AuthorDto;
import org.example.digital_library.model.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper extends BaseMapper<AuthorEntity, AuthorDto> {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Override
    AuthorDto toDto(AuthorEntity authorEntity);
    
    @Override
    AuthorEntity toEntity(AuthorDto authorDto);
}

