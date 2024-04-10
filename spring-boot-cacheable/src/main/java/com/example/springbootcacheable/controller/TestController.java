package com.example.springbootcacheable.controller;

import com.example.springbootcacheable.service.UserService;
import com.example.springbootcacheable.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public User helloWord(){
        return userService.getUserById(1L);
    }
}
