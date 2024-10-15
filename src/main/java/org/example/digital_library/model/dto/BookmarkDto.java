package org.example.digital_library.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDto {
    private Long id;
    private UserDto user;
    private Long bookId;
    private int pageNumber;
    private LocalDateTime createAt;
}

