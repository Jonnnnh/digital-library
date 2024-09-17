package org.example.digital_library.controller;

import org.example.digital_library.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

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
