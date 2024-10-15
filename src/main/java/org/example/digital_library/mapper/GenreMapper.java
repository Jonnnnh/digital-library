package org.example.digital_library.mapper;

import org.example.digital_library.model.domain.Genre;
import org.example.digital_library.model.dto.GenreDto;
import org.example.digital_library.model.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto toDto(GenreEntity genreEntity);

    GenreEntity toEntity(Genre genre);

    Genre toDomain(GenreEntity genreEntity);

    GenreEntity toEntity(GenreDto genreDto);

}
