<%--
  Created by IntelliJ IDEA.
  User: Sergei
  Date: 03.08.2022
  Time: 00:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Persons</title>
</head>
<body>
    <c:forEach items="${persons}" var="person">
        <p>${person.toString()}</p>
    </c:forEach>
</body>
</html>
