package com.example.springcloudfeign.controller;

import com.example.springcloudfeign.service.ArticleService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模拟被调用方
 */
@RestController
@RequestMapping("/article")
//全局异常回退方法
@DefaultProperties(defaultFallback = "globalFallBack")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getOne")
    public String getOne(Long id) {
        System.out.println("article 被远程调用了！");
        return articleService.getOne(id);
    }

    @GetMapping("/getList")
    public List<String> getList(List<Long> ids) {
        System.out.println("article 被远程调用了！");
        return articleService.getOneBatch(ids);
    }

    public String globalFallBack(){
        return "全局的异常处理";
    }
}
