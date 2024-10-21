package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.BookDto;
import org.example.digital_library.model.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper extends BaseMapper<BookEntity, BookDto> {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

//    @Mapping(source = "author", target = "author")
//    @Mapping(source = "genre", target = "genre")
    BookDto toDto(BookEntity bookEntity);

//    @Mapping(source = "author", target = "author")
//    @Mapping(source = "genre", target = "genre")
    BookEntity toEntity(BookDto bookDto);
}
