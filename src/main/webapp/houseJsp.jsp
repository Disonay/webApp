<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Set house</title>
</head>
<body>
    <form method="post" action="house">
        <label for="city">City:</label><br>
        <input id="city" type="text" name="city"><br>

        <label for="street">Street</label><br>
        <input type="text" id="street" name="street"><br>

        <label for="number" >Number:</label><br>
        <input type="number" id="number" min="1" name="number"><br>

        <input type="submit" value="Enter">
    </form>
</body>
</html>
