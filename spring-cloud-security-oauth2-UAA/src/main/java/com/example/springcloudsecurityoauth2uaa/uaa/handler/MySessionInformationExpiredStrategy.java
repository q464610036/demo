package com.example.springcloudsecurityoauth2uaa.uaa.handler;

import com.alibaba.fastjson.JSON;
import com.example.springcloudsecurityoauth2uaa.util.ResultUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        ResultUtil result = ResultUtil.isFail(-1, "账号已从其他设备登录");
        String json = JSON.toJSONString(result);
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }
}
