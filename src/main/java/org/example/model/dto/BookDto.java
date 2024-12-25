package org.example.model.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private AuthorDto author;
    private GenreDto genre;
    private String description;
    private String filePath;
    private LocalDate publishedDate;
}

