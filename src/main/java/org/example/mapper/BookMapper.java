package org.example.mapper;

import org.example.model.dto.BookDto;
import org.example.model.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(BookEntity bookEntity);

    BookEntity toEntity(BookDto bookDto);
}
