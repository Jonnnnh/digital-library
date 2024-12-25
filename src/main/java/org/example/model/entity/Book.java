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
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public Author getAuthor() { return author; }
//    public void setAuthor(Author author) { this.author = author; }
//
//    public Genre getGenre() { return genre; }
//    public void setGenre(Genre genre) { this.genre = genre; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public String getFilePath() { return filePath; }
//    public void setFilePath(String filePath) { this.filePath = filePath; }
//
//    public LocalDate getPublishedDate() { return publishedDate; }
//    public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }
}
