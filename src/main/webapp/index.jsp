<%@ page import="java.net.Socket" %>
<%@ page import="java.io.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    try (Socket socket = new Socket("info.cern.ch", 80)) {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Socket connected: " + socket.isConnected());

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream, true);

        printWriter.println("GET /hypertext/WWW/TheProject.html HTTP/1.1");
        printWriter.println("Host: info.cern.ch");
        printWriter.println("Connection: Close");
        printWriter.println();
        printWriter.flush();

        socket.shutdownOutput();


        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (String line : bufferedReader.lines().toList()) {
            System.out.println(line);
        }

        socket.shutdownInput();
    }
    System.out.println("----------------------------------------------------------------------------------");
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h1>Name: </h1>
<form method="post" action="hello">
    <input type="text" name="name">
    <input type="submit" value="Enter">
</form>
</body>
</html>
