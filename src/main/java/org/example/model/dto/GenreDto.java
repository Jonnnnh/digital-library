package org.example.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private Long id;
    @NotEmpty(message = "Genre name cannot be empty")
    private String name;
}
