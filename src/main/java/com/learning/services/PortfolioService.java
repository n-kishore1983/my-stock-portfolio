package com.learning.services;

import com.learning.tools.ToolsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PortfolioService {

    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    private final ChatClient chatClient;
    private final ToolsService toolsService;

    public PortfolioService(ChatClient.Builder chatClientBuilder, ToolsService toolsService) {
        this.chatClient = chatClientBuilder.build();
        this.toolsService = toolsService;
    }


    public String askQuestion(String question) {
        log.info("Asking question {}", question);
        return chatClient.prompt()
                .user(question)
                .tools(toolsService)
                .call()
                .content();
    }
}

