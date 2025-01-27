package org.example.mapper;

import org.example.model.dto.BookDto;
import org.example.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "genre", source = "genre")
    BookDto toDto(Book bookEntity);
    @Mapping(target = "author", source = "author")
    @Mapping(target = "genre", source = "genre")
    Book toEntity(BookDto bookDto);
}
