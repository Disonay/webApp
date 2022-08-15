<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dynamic hello</title>
</head>
<body>
<h1>Name: </h1>
<form method="post" action="/hello">
    <input type="text" name="name">
    <input type="submit" value="Enter">
</form>
</body>
</html>