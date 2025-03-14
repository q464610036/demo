package com.example.springaideepseek;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiDeepseekApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiDeepseekApplication.class, args);
    }

    @Bean
    public ChatMemory chatMemory(){
        return new InMemoryChatMemory();
    }
}
