package org.example.model.dto;

import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
