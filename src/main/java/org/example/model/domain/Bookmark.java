package org.example.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.dto.BookDto;
import org.example.model.dto.UserDto;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {
    private Long id;
    private UserDto user;
    private BookDto book;
    private int pageNumber;
    private LocalDateTime createAt;
}
