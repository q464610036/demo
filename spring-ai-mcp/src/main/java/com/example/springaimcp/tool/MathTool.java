package com.example.springaimcp.tool;

import org.springframework.ai.tool.annotation.Tool;

public class MathTool {
    @Tool(description = "加法")
    public Integer addNumber(Integer a, Integer b) {
        return a + b;
    }

    @Tool(description = "java MCP")
    public String helloMCP(String message) {
        return "这是java的MCP";
    }
}
