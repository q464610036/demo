package com.example.springmvc.controller;

import com.example.springmvc.dto.UserDTO;
import com.example.springmvc.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDTO dto, HttpSession session){
        //校验登录
        authenticationService.authentication(dto);
        session.setAttribute("_user", dto);
        return "index";
    }
}
