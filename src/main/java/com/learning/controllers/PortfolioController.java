package com.learning.controllers;

import com.learning.services.PortfolioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String question) {
        return portfolioService.askQuestion(question);
    }
}

