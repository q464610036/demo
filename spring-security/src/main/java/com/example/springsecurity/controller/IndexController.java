package com.example.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    @ResponseBody
    public Object user(){
        //获取security上下文
        SecurityContext securityContext = SecurityContextHolder.getContext();
        //获取认证信息
        Authentication authentication = securityContext.getAuthentication();
        //获取用户身份信息
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)principal;
            System.out.println("userName:"+userDetails.getUsername());
        } else {
            System.out.println("principalStr:"+principal.toString());
        }
        //获取用户授权信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //用户凭证：密码
        Object credentials = authentication.getCredentials();
        Map result = new HashMap();
        result.put("principal", principal);
        result.put("authorities", authorities);
        result.put("credentials", credentials);
        return result;
    }
}
