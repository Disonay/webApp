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
