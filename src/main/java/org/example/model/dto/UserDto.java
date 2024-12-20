package org.example.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
}
