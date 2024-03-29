package com.example.springcloudfeign.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@DefaultProperties
public class ArticleController {

    @GetMapping("/start")
    public String userStart() {
        System.out.println("article 被远程调用了！");
        return "article ok ~";
    }

}
