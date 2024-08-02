package com.atguigu;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.io.IOException;


@WebFilter(urlPatterns = "/signdecodein")
public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            // 通过工具给二者加密
            Base64.Encoder encoder = Base64.getEncoder();
            String usernameEncoded = encoder.encodeToString(username.getBytes(StandardCharsets.UTF_8));
            String passwordEncoded = encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));
            request.setAttribute("username", usernameEncoded);
            request.setAttribute("password", passwordEncoded);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
