package org.example.mapper;

import org.example.model.dto.BookmarkDto;
import org.example.model.entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface BookmarkMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "user", target = "user")
    BookmarkDto toDto(Bookmark bookmark);
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "createdAt", target = "createdAt")
    Bookmark toEntity(BookmarkDto bookmarkDto);
}
