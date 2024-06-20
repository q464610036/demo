package com.example.springsecurity.module.user.controller;

import com.example.springsecurity.common.util.ResultUtil;
import com.example.springsecurity.module.user.dto.AccountDTO;
import com.example.springsecurity.module.user.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
