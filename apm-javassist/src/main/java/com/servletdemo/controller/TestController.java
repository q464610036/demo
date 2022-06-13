package com.servletdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/index")
    public String index(){
        return "ok";
    }
}
