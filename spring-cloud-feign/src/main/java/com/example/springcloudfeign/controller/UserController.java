package com.example.springcloudfeign.controller;

import com.example.springcloudfeign.client.ArticleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ArticleClient articleClient;

    @GetMapping("/start")
    public String userStart() {
        String result = articleClient.userStart();
        return "user ok ~\n" + result;
    }

}
