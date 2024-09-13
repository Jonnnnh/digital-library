package org.example.digital_library.controller;

import jakarta.validation.Valid;
import org.example.digital_library.model.Genre;
import org.example.digital_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/new")
    public String showAddGenreForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "add_genre";
    }

    @PostMapping
    public String addGenre(@ModelAttribute("genre") @Valid Genre genre, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_genre";
        }
        genreService.createGenre(genre);
        return "redirect:/books/new";  // Возвращаемся к форме создания книги
    }

    @GetMapping("/{id}")
    public String getGenreById(@PathVariable Long id, Model model) {
        Genre genre = genreService.getGenreById(id);
        model.addAttribute("genre", genre);
        return "genre_details";
    }
}
