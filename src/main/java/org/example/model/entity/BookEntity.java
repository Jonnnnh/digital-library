package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreEntity genre;

    @Column
    private String description;

    @Column
    private String filePath;

    @Column(nullable = false)
    private LocalDate publishedDate;
}

