package org.example.digital_library.model.domain;


import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private Author author;
    private Genre genre;
    private String description;
    private String filePath;
    private LocalDate publishedDate;
    private User user;
}
