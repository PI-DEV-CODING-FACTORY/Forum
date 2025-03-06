package com.esprit.pidev.codingfactory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.esprit.pidev.codingfactory.chatgptBot.dto.ChatgptRequest;
import com.esprit.pidev.codingfactory.chatgptBot.dto.ChatgptResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bot")
public class BotController {

    private String model = "gpt-4o-mini";

    private String apiURL = "https://api.openai.com/v1/chat/completions";

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        ChatgptRequest request = new ChatgptRequest(model, prompt);
        ChatgptResponse chatGptResponse = template.postForObject(apiURL, request, ChatgptResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}