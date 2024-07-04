package com.atguigu;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("<h1>Sign In</h1>");
        out.write("<form action=\"/cookie\" method=\"post\">");
        out.write("<p>Username: <input name=\"username\"></p>");
        out.write("<p>Password: <input name=\"password\" type=\"password\"></p>");
        out.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
        out.write("</form>");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie username = new Cookie("username", URLEncoder.encode(req.getParameter("username"), "UTF-8"));
        Cookie password = new Cookie("password", URLEncoder.encode(req.getParameter("password"), "UTF-8"));
        username.setPath("/");
        password.setPath("/password");
        username.setMaxAge(8640000);
        resp.addCookie(username);
        resp.addCookie(password);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("<h1>Cookie Test</h1>");
        out.flush();
    }
}
