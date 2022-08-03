<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Sergei
  Date: 25.07.2022
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/person-house">
        <label for="house">Select house:</label><br>
        <select name="house" id="house">
            <c:forEach items="${houses}" var="house">
                <option value="${house.id}">
                    <c:out value="${house.toString()}"/>
                </option>
            </c:forEach>
        </select>
        
        <br><br>
        
        <label for="persons">Select persons:</label><br>
        <select name="persons" id="persons" multiple>
            <c:forEach items="${persons}" var="person">
                <option value="${person.id}">
                    <c:out value="${person.toString()}"/>
                </option>
            </c:forEach>
        </select>
        
        <br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
