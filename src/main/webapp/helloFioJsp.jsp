<%@ page import="java.util.Objects" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %><%--
  Created by IntelliJ IDEA.
  User: Sergei
  Date: 12.07.2022
  Time: 02:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
    <%
        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        String middleName = (String) session.getAttribute("middleName");
    %>
    <%="Hello, " + surname + " " + name + " " + middleName + "!"%>
</body>
</html>
