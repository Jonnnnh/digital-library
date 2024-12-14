package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/stats")
    public String showStatistics(Model model) {
        long totalBooks = statisticsService.getTotalBooks();
        String mostPopularGenre = statisticsService.getMostPopularGenre();
        String mostActiveUser = statisticsService.getMostActiveUser();

        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("mostPopularGenre", mostPopularGenre);
        model.addAttribute("mostActiveUser", mostActiveUser);

        return "statistics";
    }
}
