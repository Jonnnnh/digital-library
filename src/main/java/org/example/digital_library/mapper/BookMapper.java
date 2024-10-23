package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.BookDto;
import org.example.digital_library.model.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(BookEntity bookEntity);

    BookEntity toEntity(BookDto bookDto);
}
