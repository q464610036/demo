package com.example.springcloudfeign.controller;

import com.example.springcloudfeign.service.ArticleService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟被调用方
 */
@RestController
@RequestMapping("/article")
@DefaultProperties
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getOne")
    public String getOne() {
        System.out.println("article 被远程调用了！");
        return articleService.execute();
    }

    @GetMapping("/getList")
    public String getList() {
        System.out.println("article 被远程调用了！");
        return articleService.execute();
    }
}
