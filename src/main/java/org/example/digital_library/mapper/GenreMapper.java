package org.example.digital_library.mapper;

import org.example.digital_library.model.domain.Genre;
import org.example.digital_library.model.dto.GenreDto;
import org.example.digital_library.model.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper extends BaseMapper<GenreEntity, GenreDto> {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    @Override
    GenreDto toDto(GenreEntity genreEntity);

    @Override
    GenreEntity toEntity(GenreDto genreDto);

}
