package org.example.digital_library.model.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private Long id;
    private String name;
}
