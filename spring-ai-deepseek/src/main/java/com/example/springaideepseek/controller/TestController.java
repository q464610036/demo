package com.example.springaideepseek.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.context.ContextView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class TestController {

    ChatClient chatClient;

    public TestController(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder
                //每次用户发消息的时候拦截一下，把chatMemory的聊天记录丢给大模型，交给大模型结合思考
                .defaultAdvisors(new PromptChatMemoryAdvisor(chatMemory))
                // 预设角色（预设了角色后面好像就无法改了）
                // 默认系统消息，即本段文案每次发送给大模型。
                // 也可以在Controller方法发送时发送系统消息，这样不同的Controller方法就可以设置不同的系统角色
//                .defaultSystem("您是“安晴”中学的客户聊天支持代理。请以友好、乐于助人且愉快的方式来回复。\n" +
//                        "您正在通过在线聊天系统与客户互动。\n" +
//                        "请讲中文。\n" +
//                        "今天的日期是{now}")
                .build();
    }
    /**
     * 学校角色 完全响应
     * @param message
     * @return
     */
    @CrossOrigin
    @GetMapping("ai/school")
    public String school(@RequestParam(value = "message") String message){
        String userId = "001";
        return chatClient
                .prompt()
                .user(message)
                .system(a -> a.param("now", LocalDateTime.now().toString()))
                .system("您是“安晴”学校的客户聊天支持代理。请以友好、乐于助人且愉快的方式来回复。\n" +
                        "您正在通过在线聊天系统与客户互动。\n" +
                        "请讲中文。\n" +
                        "今天的日期是{now}")
                //聊天记录按用户隔离
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, userId))
                //设置存储聊天记录的条数，默认100
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .call()
                .content();
    }

    /**
     * 航空公司角色 完全响应
     * @param message
     * @return
     */
    @CrossOrigin
    @GetMapping("ai/airline")
    public String airline(@RequestParam(value = "message") String message){
        String userId = "001";
        Map<String, Object> params = new HashMap<>();
        params.put("now", LocalDateTime.now().toString());
        return chatClient
                .prompt()
                .user(message)
                .system(a -> a.params(params))
                .system("您是“安晴”航空公司的客户聊天支持代理。请以友好、乐于助人且愉快的方式来回复。\n" +
                        "您正在通过在线聊天系统与客户互动。\n" +
                        "请讲中文。\n" +
                        "今天的日期是{now}")
                //聊天记录按用户隔离
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, userId))
                //设置存储聊天记录的条数，默认100
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .call()
                .content();
    }

    /**
     * 流式响应
     * @param message
     * @return
     */
    @CrossOrigin
    @GetMapping("ai/test2")
    public Flux<String> test2(@RequestParam(value = "message") String message){
        Flux<String> content = chatClient.prompt().user(message).stream().content();
        return content;
//        return content.contextWrite(Flux.just("[complete]"));
    }

    @GetMapping("test")
    public Flux<String> test(@RequestParam(value = "message") String message){
        while(message.equals("1")) {
            System.out.println(Thread.currentThread().getName());
        }
        Flux<String> content = chatClient.prompt().user(message).stream().content();
        return content;
//        return content.contextWrite(Flux.just("[complete]"));
    }
}
