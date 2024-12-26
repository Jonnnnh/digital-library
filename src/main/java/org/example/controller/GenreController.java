package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.GenreDto;
import org.example.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/new")
    public String showAddGenreForm(Model model) {
        model.addAttribute("genre", new GenreDto());
        return "add_genre";
    }

    @PostMapping
    public String addGenre(@ModelAttribute("genre") @Valid GenreDto genreDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_genre";
        }
        log.info("Genre name: {}", genreDto.getName());
        genreService.createGenre(genreDto);
        return "redirect:/books/new";
    }

    @GetMapping("/{id}")
    public String getGenreById(@PathVariable Long id, Model model) {
        GenreDto genre = genreService.getGenreById(id);
        if (genre != null) {
            model.addAttribute("genre", genre);
            return "genre_details";
        } else {
            model.addAttribute("error", "Genre not found");
            return "error";
        }
    }
}
