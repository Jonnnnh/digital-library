package org.example.digital_library.mapper;

import org.example.digital_library.model.dto.UserDto;
import org.example.digital_library.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);
}
