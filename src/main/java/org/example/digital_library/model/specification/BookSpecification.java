package org.example.digital_library.model.specification;

import org.example.digital_library.model.entity.BookEntity;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<BookEntity> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<BookEntity> hasGenre(String genre) {
        return (root, query, criteriaBuilder) -> {
            if (genre == null || genre.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("genre").get("name"), genre);
        };
    }

    public static Specification<BookEntity> hasAuthor(String authorLastName) {
        return (root, query, criteriaBuilder) -> {
            if (authorLastName == null || authorLastName.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("author").get("lastName"), "%" + authorLastName + "%");
        };
    }
}
