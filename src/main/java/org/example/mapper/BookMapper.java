package org.example.mapper;

import org.example.model.dto.BookDto;
import org.example.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper {
    BookDto toDto(Book bookEntity);
    Book toEntity(BookDto bookDto);
}
