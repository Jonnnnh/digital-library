package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.BookmarkDto;
import org.example.model.dto.UserDto;
import org.example.service.BookmarkService;
import org.example.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final UserService userService;
    private final BookmarkService bookmarkService;

    @GetMapping("/{bookId}/bookmark/new")
    public String showBookmarkForm(@PathVariable Long bookId, Model model) {
        log.info("Opening bookmark form for book ID: {}", bookId);
        BookmarkDto bookmarkDto = new BookmarkDto();
        bookmarkDto.setBookId(bookId);
        model.addAttribute("bookmark", bookmarkDto);
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
        if (userDto == null) {
            log.error("User not found with username: {}", username);
            model.addAttribute("error", "User not found.");
            return "error";
        }
        bookmarkDto.setBookId(bookId);
        bookmarkDto.setUser(userDto);
        bookmarkDto.setCreatedAt(LocalDateTime.now());

        log.info("Saving bookmark for book ID: {} and user ID: {}", bookId, userDto.getId());
        bookmarkService.addBookmark(bookmarkDto);
        return "redirect:/books/" + bookId;
    }

    @GetMapping("/user")
    public String getUserBookmarks(Authentication authentication, Model model) {
        String username = authentication.getName();
        log.info("Fetching bookmarks for user: {}", username);
        UserDto userDto = userService.findUserByUsername(username);
        if (userDto == null) {
            log.error("User not found with username: {}", username);
            model.addAttribute("error", "User not found");
            return "error";
        }

        List<BookmarkDto> bookmarks = bookmarkService.getUserBookmarks(userDto.getId());
        model.addAttribute("bookmarks", bookmarks);
        return "bookmarks";
    }

    @DeleteMapping("/{bookmarkId}/delete")
    public String deleteBookmark(@PathVariable Long bookmarkId, @RequestParam Long bookId, Model model) {
        try {
            log.info("Deleting bookmark with ID: {}", bookmarkId);
            bookmarkService.deleteBookmark(bookmarkId);
        } catch (Exception e) {
            log.error("Error deleting bookmark with ID: {}", bookmarkId, e);
            model.addAttribute("error", "An error occurred while deleting the bookmark. Please try again");
            return "error";
        }
        return "redirect:/books/" + bookId;
    }

}
