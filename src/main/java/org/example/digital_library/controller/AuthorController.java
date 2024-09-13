package org.example.digital_library.controller;

import jakarta.validation.Valid;
import org.example.digital_library.model.Author;
import org.example.digital_library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PostMapping
    public String addAuthor(@ModelAttribute("author") @Valid Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_author";
        }
        authorService.createAuthor(author);
        return "redirect:/books/new";  // Возвращаемся к форме создания книги
    }

    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "author_details";
    }
}


