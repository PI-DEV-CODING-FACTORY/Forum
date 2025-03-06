package com.esprit.pidev.codingfactory.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class HackerNewsService {
    private static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";

    private final RestTemplate restTemplate;

    public HackerNewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Récupérer les IDs des articles populaires
    @SuppressWarnings("unchecked")
    public List<Integer> getTopStories() {
        String url = BASE_URL + "topstories.json";
        return restTemplate.getForObject(url, List.class);
    }

    // Récupérer les détails d'un article
    public Object getArticleDetails(int id) {
        String url = BASE_URL + "item/" + id + ".json";
        return restTemplate.getForObject(url, Object.class);
    }
}
