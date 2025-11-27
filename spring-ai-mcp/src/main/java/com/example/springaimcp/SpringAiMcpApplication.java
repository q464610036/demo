package com.example.springaimcp;

import com.example.springaimcp.tool.MathTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiMcpApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider mathTool() {
        return MethodToolCallbackProvider.builder().toolObjects(new MathTool()).build();
    }
}
