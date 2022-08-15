<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
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
