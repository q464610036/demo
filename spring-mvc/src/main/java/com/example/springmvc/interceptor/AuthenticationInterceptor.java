package com.example.springmvc.interceptor;

import com.example.springmvc.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aopalliance.intercept.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    /**
     * 请求之前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("_user");
        if (obj == null) {
            this.writeContent(response, "请登录");
        }
        UserDTO user = (UserDTO) obj;
        //判断用户权限
        String url = request.getRequestURI();
        if (user.getAuthorities().contains("user") && url.equals("/user/index")) {
            return true;
        }
        if (user.getAuthorities().contains("order") && url.equals("/order/index")) {
            return true;
        }
        this.writeContent(response, "没有权限");
        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(msg);
        writer.close();
        response.resetBuffer();
    }
}
