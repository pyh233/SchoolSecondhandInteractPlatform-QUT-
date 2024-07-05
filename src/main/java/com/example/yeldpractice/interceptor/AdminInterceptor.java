package com.example.yeldpractice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object adminAccessID = session.getAttribute("adminAccessID");

        if (adminAccessID == null) {
            // 如果没有管理员信息，重定向到管理员登录页面
            response.sendRedirect(request.getContextPath() + "/Admin/adminLogin.html");
            return false;
        }
        return true;
    }
}
