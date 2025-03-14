package com.example.springaideepseek.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.chat.MessageFormat;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ai/ocr")
public class ImageRecognitionController {
    @Autowired
    private ChatModel chatModel;

    private static final String DEFAULT_MODEL = "qwen-vl-max-latest";
//    private static final String DEFAULT_MODEL = "deepseek-r1";


    @GetMapping("/imageRecognition")
    public String imageRecognition(
            @RequestParam(value = "prompt", required = false, defaultValue = "文字识别") String prompt,
            HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        InputStream in = new FileInputStream("D://work/test/ocr.png");
        Resource imageResource = new InputStreamResource(in);
        List<Media> mediaList = List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageResource));
        UserMessage message = new UserMessage(prompt, mediaList);
        message.getMetadata().put(DashScopeChatModel.MESSAGE_FORMAT, MessageFormat.IMAGE);
        ChatResponse result = chatModel.call(
                new Prompt(message, DashScopeChatOptions.builder()
                        .withModel(DEFAULT_MODEL)
                        .withMultiModel(true)
                        .build()));
        return result.toString();
    }

    /*
    @GetMapping("/imageRecognition")
    public Flux<String> imageRecognition(
            @RequestParam(value = "prompt", required = false, defaultValue = "文字识别") String prompt,
            HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        InputStream in = new FileInputStream("D://work/test/ocr.png");
        Resource imageResource = new InputStreamResource(in);
        List<Media> mediaList = List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageResource));
        UserMessage message = new UserMessage(prompt, mediaList);
        message.getMetadata().put(DashScopeChatModel.MESSAGE_FORMAT, MessageFormat.IMAGE);
        Flux<ChatResponse> fluxResponse = chatModel.stream(
                new Prompt(message, DashScopeChatOptions.builder()
                        .withModel(DEFAULT_MODEL)
                        .withMultiModel(true)
                        .build()));
        return fluxResponse.map(resp -> resp.getResult().getOutput().getContent());
    }
     */
}
