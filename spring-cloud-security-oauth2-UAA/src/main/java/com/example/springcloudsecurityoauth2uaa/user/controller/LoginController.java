package com.example.springcloudsecurityoauth2uaa.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenmengfei
 * @since 2024-05-23
 */
@Controller
public class LoginController {

    @GetMapping("/myLogin")
    public String login() {
        return "login";
    }

}
