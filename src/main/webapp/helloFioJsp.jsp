<%@ page contentType="text/html;charset=UTF-8" %>
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
