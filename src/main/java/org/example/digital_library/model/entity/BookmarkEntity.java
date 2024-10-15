package org.example.digital_library.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookmarks")
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(nullable = false)
    private int pageNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
