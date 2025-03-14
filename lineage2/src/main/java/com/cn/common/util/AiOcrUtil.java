package com.cn.common.util;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.chat.MessageFormat;
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

public class AiOcrUtil {

    static ChatModel chatModel = null;
    private static final String DEFAULT_MODEL = "qwen-vl-max-latest";

    public static String ocr(String path) throws FileNotFoundException {
        DashScopeApi dashScopeApi = new DashScopeApi("sk-4dede2a8fb4f4fd8a0a4187f99112f00");
        if (chatModel == null) {
            chatModel = new DashScopeChatModel(dashScopeApi);
        }
        InputStream in = new FileInputStream(path);
        Resource imageResource = new InputStreamResource(in);
        List<Media> mediaList = List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageResource));
        UserMessage message = new UserMessage("文字识别", mediaList);
        message.getMetadata().put(DashScopeChatModel.MESSAGE_FORMAT, MessageFormat.IMAGE);
        ChatResponse response = chatModel.call(
                new Prompt(message, DashScopeChatOptions.builder()
                        .withModel(DEFAULT_MODEL)
                        .withMultiModel(true)
                        .build()));
        return response.getResult().getOutput().getText();
    }


}
