package com.esprit.pidev.codingfactory.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.pidev.codingfactory.Service.HackerNewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
public class HackerNewsController {
    private final HackerNewsService hackerNewsService;

    public HackerNewsController(HackerNewsService hackerNewsService) {
        this.hackerNewsService = hackerNewsService;
    }

    // Endpoint pour récupérer les IDs des articles populaires
    @GetMapping("/api/top-stories")
    public List<Integer> getTopStories() {
        return hackerNewsService.getTopStories();
    }

    // Endpoint pour récupérer les détails d'un article
    @GetMapping("/api/article/{id}")
    public Object getArticleDetails(@PathVariable int id) {
        return hackerNewsService.getArticleDetails(id);
    }
}
