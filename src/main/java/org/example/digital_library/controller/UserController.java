package org.example.digital_library.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.digital_library.model.dto.UserDto;
import org.example.digital_library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.createUser(userDto);
        return "redirect:/login";
    }
}
