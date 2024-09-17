package org.example.digital_library.controller;

import jakarta.validation.Valid;
import org.example.digital_library.model.Author;
import org.example.digital_library.model.Genre;
import org.example.digital_library.service.AuthorService;
import org.example.digital_library.service.BookService;
import org.example.digital_library.model.Book;
import org.example.digital_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;


    @GetMapping
    public String getAllBooks(@RequestParam(required = false) String title,
                              @RequestParam(required = false) Long authorId,
                              @RequestParam(required = false) Long genreId,
                              Model model) {
        List<Book> books = bookService.searchBooks(title, authorId, genreId);
        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "books";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book_form";
    }

    @PostMapping
    public String saveOrUpdateBook(@ModelAttribute("book") @Valid Book book,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("genres", genreService.getAllGenres());
            return "book_form";
        }

        book.setFilePath("/files/temp.txt");
        bookService.save(book);

        String filePath = "/files/book_" + book.getId() + ".txt";
        book.setFilePath(filePath);

        bookService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "author_details";
    }

    @GetMapping("/genres")
    public String getAllGenres(Model model) {
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
        return "genre_details";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book_detail";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            bookService.deleteBook(id);
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while deleting the book. Please try again.");
            return "books";
        }
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String title,
                              @RequestParam(required = false) Long genreId,
                              @RequestParam(required = false) Long authorId,
                              Model model) {
        List<Book> books = bookService.searchBooks(title, authorId, genreId);
        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "books";
    }

}

