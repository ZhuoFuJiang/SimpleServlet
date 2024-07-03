package com.atguigu;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    private Map<String, String> users = new HashMap<>();

    @Override
    public void init() throws ServletException {
        // 初始化users
        users.put("zhangsan", "12345");
        users.put("lisi", "23456");
        users.put("wangwu", "34567");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        out.write("<h1>Sign In</h1>");
        out.write("<form action=\"/signin\" method=\"post\">");
        out.write("<p>Username: <input name=\"username\"></p>");
        out.write("<p>Password: <input name=\"password\" type=\"password\"></p>");
        out.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
        out.write("</form>");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String expectedPassword = users.get(name.toLowerCase());
        if(expectedPassword != null && expectedPassword.equals(password)) {
            req.getSession().setAttribute("user", name);
            resp.sendRedirect("/");
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
