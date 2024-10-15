package org.example.digital_library.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private Long id;
    private String name;
}
