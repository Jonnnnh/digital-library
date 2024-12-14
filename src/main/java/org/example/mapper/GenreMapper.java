package org.example.mapper;

import org.example.model.dto.GenreDto;
import org.example.model.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto toDto(GenreEntity genreEntity);

    GenreEntity toEntity(GenreDto genreDto);

}
