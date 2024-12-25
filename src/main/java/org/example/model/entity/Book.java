package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @Column
    private String description;

    @Column
    private String filePath;

    @Column(nullable = false)
    private LocalDate publishedDate;
}

