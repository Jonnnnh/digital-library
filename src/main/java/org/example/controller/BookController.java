package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.*;
import org.example.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final UserService userService;
    private final BookmarkService bookmarkService;

    @GetMapping
    public String getAllBooks(@RequestParam(required = false) String title,
                              @RequestParam(required = false) Long authorId,
                              @RequestParam(required = false) Long genreId,
                              Model model) {
        List<BookDto> books = bookService.getAllBooks(title, authorId, genreId);
        books.forEach(book -> {
        });
        model.addAttribute("books", books);

        List<AuthorDto> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);

        List<GenreDto> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);

        model.addAttribute("title", title);
        model.addAttribute("authorId", authorId);
        model.addAttribute("genreId", genreId);

        return "books";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        log.info("Opening form to create a new book");
        model.addAttribute("book", new BookDto());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book_form";
    }

    @PostMapping
    public String saveOrUpdateBook(@ModelAttribute("book") @Valid BookDto bookDto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            log.warn("Validation failed for book with errors: {}", result.getAllErrors());
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("genres", genreService.getAllGenres());
            return "book_form";
        }
        bookService.save(bookDto);
        return "redirect:/books";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        log.info("Opening form to edit book with ID: {}", id);
        BookDto bookDto = bookService.getBookById(id);
        if (bookDto == null) {
            log.error("Book with ID: {} not found", id);
            model.addAttribute("error", "Book not found");
            return "redirect:/books";
        }
        model.addAttribute("book", bookDto);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book_form";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Authentication authentication, Model model) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);
        model.addAttribute("bookId", id);
        model.addAttribute("bookmark", new BookmarkDto());
        UserDto userDto = userService.findUserByUsername(authentication.getName());
        List<BookmarkDto> bookmarks = bookmarkService.getUserBookmarksForBook(id, userDto.getId());
        model.addAttribute("bookmarks", bookmarks);
        return "book_detail";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            log.info("Deleting book with ID: {}", id);
            bookService.deleteBook(id);
        } catch (Exception e) {
            log.error("Error deleting book with ID: {}", id, e);
            model.addAttribute("error", "An error occurred while deleting the book. Please try again");
            return "books";
        }
        return "redirect:/books";
    }
}
