package org.example.digital_library.model;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Book> hasGenre(String genre) {
        return (root, query, criteriaBuilder) -> {
            if (genre == null || genre.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("genre").get("name"), genre);
        };
    }

    public static Specification<Book> hasAuthor(String authorLastName) {
        return (root, query, criteriaBuilder) -> {
            if (authorLastName == null || authorLastName.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("author").get("lastName"), "%" + authorLastName + "%");
        };
    }
}

