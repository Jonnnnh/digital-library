package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.BookmarkDto;
import org.example.digital_library.model.entity.BookmarkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookmarkMapper {
    BookmarkMapper INSTANCE = Mappers.getMapper(BookmarkMapper.class);

    BookmarkDto toDto(BookmarkEntity bookmarkEntity);

    BookmarkEntity toEntity(BookmarkDto bookmarkDto);
}
