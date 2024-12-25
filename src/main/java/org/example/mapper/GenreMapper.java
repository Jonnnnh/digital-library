package org.example.mapper;

import org.example.model.dto.GenreDto;
import org.example.model.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {
    GenreDto toDto(Genre genreEntity);
    Genre toEntity(GenreDto genreDto);

}
