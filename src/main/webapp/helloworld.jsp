<%--
  Created by IntelliJ IDEA.
  User: 蒋卓甫
  Date: 2024/7/4
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World - JSP</title>
</head>
<body>
<%-- JSP Comment --%>
<h1>Hello World!</h1>
<p>
    <%
        out.println("Your IP address is ");
    %>
    <span style="color:red">
        <%= request.getRemoteAddr() %>
    </span>
</p>
</body>
</html>
