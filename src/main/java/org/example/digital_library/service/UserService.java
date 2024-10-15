package org.example.digital_library.service;

import lombok.AllArgsConstructor;
import org.example.digital_library.mapper.UserMapper;
import org.example.digital_library.model.dto.UserDto;
import org.example.digital_library.model.entity.UserEntity;
import org.example.digital_library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public void createUser(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPasswordHash(userDto.getPasswordHash());

        userRepository.save(userEntity);
    }

    public UserDto findUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }

}
