package org.example.digital_library.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.digital_library.model.dto.BookmarkDto;
import org.example.digital_library.model.dto.UserDto;
import org.example.digital_library.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final UserService userService;
    private final BookmarkService bookmarkService;

    @GetMapping("/{bookId}/bookmark/new")
    public String showBookmarkForm(@PathVariable Long bookId, Model model) {
        log.info("Opening bookmark form for book ID: {}", bookId);
        model.addAttribute("bookmark", new BookmarkDto());
        model.addAttribute("bookId", bookId);
        return "bookmark_form";
    }

    @PostMapping("/{bookId}/bookmark")
    public String addBookmark(@PathVariable Long bookId,
                              @ModelAttribute("bookmark") @Valid BookmarkDto bookmarkDto,
                              BindingResult result,
                              Authentication authentication,
                              Model model) {
        if (result.hasErrors()) {
            log.warn("Validation failed for bookmark with errors: {}", result.getAllErrors());
            model.addAttribute("bookId", bookId);
            return "bookmark_form";
        }

        String username = authentication.getName();
        log.info("Adding bookmark for user: {}", username);

        UserDto userDto = userService.findUserByUsername(username);

        bookmarkDto.setBookId(bookId);
        bookmarkDto.setUser(userDto);
        bookmarkDto.setCreateAt(LocalDateTime.now());

        log.info("Saving bookmark for book ID: {} and user ID: {}", bookId, userDto.getId());
        bookmarkService.addBookmark(bookmarkDto);

        return "redirect:/books/" + bookId;
    }


    @DeleteMapping("/{bookmarkId}/delete")
    public String deleteBookmark(@PathVariable Long bookmarkId, Model model) {
        try {
            log.info("Deleting bookmark with ID: {}", bookmarkId);
            bookmarkService.deleteBookmark(bookmarkId);
        } catch (Exception e) {
            log.error("Error deleting bookmark with ID: {}", bookmarkId, e);
            model.addAttribute("error", "An error occurred while deleting the bookmark. Please try again.");
            return "error";
        }
        return "redirect:/bookmarks";
    }

}
