package org.example.mapper;

import org.example.model.dto.BookmarkDto;
import org.example.model.entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookmarkMapper {
    BookmarkDto toDto(Bookmark bookmarkEntity);
    Bookmark toEntity(BookmarkDto bookmarkDto);
}
