package org.example.digital_library.model.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
