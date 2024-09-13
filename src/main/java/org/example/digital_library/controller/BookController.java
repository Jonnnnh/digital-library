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
        model.addAttribute("book", new Book());  // Объект Book добавлен в модель
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book_form";  // Имя шаблона
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
                                   @RequestParam(value = "filePath", required = false) MultipartFile file,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("genres", genreService.getAllGenres());
            return "book_form";
        }

        // Обработка файла, если он загружен
        if (file != null && !file.isEmpty()) {
            // Проверяем, что файл в формате .txt и его размер не превышает 2MB
            if (!file.getContentType().equals("text/plain")) {
                model.addAttribute("error", "Only .txt files are allowed.");
                return "book_form";
            }
            if (file.getSize() > 2 * 1024 * 1024) { // 2MB
                model.addAttribute("error", "File size must be less than 2MB.");
                return "book_form";
            }

            try {
                // Создаем директорию, если она не существует
                String uploadDir = "uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                // Сохраняем файл на диск
                Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
                Files.write(filePath, file.getBytes());

                // Устанавливаем путь к файлу в объект книги
                book.setFilePath(filePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error saving file. Please try again.");
                return "book_form";
            }
        }

        // Сохранение книги
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

