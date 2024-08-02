package com.atguigu;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebFilter(urlPatterns = "/hello")
public class CacheFilter implements Filter {
    private Map<String, byte[]> cache = new HashMap<String, byte[]>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        byte[] data = this.cache.get(url);
        response.setHeader("X-Cache-Hit", data == null ? "No" : "Yes");
        if(data == null) {
            // 缓存未找到，构造一个伪造的Response

        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}


//class CachedHttpServletRequest extends HttpServletRequestWrapper {
//    private boolean open = false;
//    private ByteArrayOutputStream output = new ByteArrayOutputStream();
//
//}
