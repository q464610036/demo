package com.example.springcloudsecurityoauth2uaa.uaa.handler;

import com.alibaba.fastjson.JSON;
import com.example.springcloudsecurityoauth2uaa.util.ResultUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();//获取用户身份信息
        //以下两个不会返回给前端
        Object credentials = authentication.getCredentials();//获取用户凭证信息，例如用户密码
        Collection<? extends GrantedAuthority> getAuthorities = authentication.getAuthorities();//获取用户权限信息
        ResultUtil result = new ResultUtil(10000, "认证成功", true, principal);
        String json = JSON.toJSONString(result);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }
}
