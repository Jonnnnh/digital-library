package org.example.mapper;

import org.example.model.dto.UserDto;
import org.example.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);
}
