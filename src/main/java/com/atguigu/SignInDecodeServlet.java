package com.atguigu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/signdecodein")
public class SignInDecodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        out.write("<h1>Sign In</h1>");
        out.write("<form action=\"/signdecodein\" method=\"post\">");
        out.write("<p>Username: <input name=\"username\"></p>");
        out.write("<p>Password: <input name=\"password\" type=\"password\"></p>");
        out.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
        out.write("</form>");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameEncoded = (String) req.getAttribute("username");
        String passwordEncoded =  (String) req.getAttribute("password");
        System.out.println("username: " + usernameEncoded + " password: " + passwordEncoded);
        // 开始解密
        Base64.Decoder decoder = Base64.getDecoder();
        String username = new String(decoder.decode(usernameEncoded), StandardCharsets.UTF_8);
        String password = new String(decoder.decode(passwordEncoded), StandardCharsets.UTF_8);
        System.out.println("username: " + username + " password: " + password);
    }
}
