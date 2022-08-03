<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Sergei
  Date: 26.07.2022
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${houses}" var="house">
        <h1>${house.toString()}</h1>
        <c:forEach items="${house.getPersons()}" var="person">
            <p>${person.toString()}</p>
        </c:forEach>
        <br>
    </c:forEach>
</body>
</html>
