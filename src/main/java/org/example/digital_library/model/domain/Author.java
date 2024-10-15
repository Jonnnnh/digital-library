package org.example.digital_library.model.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
