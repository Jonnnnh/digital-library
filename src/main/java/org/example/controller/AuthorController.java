package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.AuthorDto;
import org.example.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new AuthorDto());
        return "add_author";
    }

    @PostMapping
    public String addAuthor(@ModelAttribute("author") @Valid AuthorDto authorDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_author";
        }
        authorService.createAuthor(authorDto);
        return "redirect:/authors";
    }

    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable Long id, Model model) {
        AuthorDto authorDto = authorService.getAuthorById(id);
        if (authorDto == null) {
            return "error";
        }
        model.addAttribute("author", authorDto);
        return "author_details";
    }

    @GetMapping
    public String getAllAuthors(Model model) {
        List<AuthorDto> authorDtos = authorService.getAllAuthors();
        model.addAttribute("authors", authorDtos);
        return "redirect:/books/new";
    }
}
