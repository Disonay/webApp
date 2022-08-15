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
    <title>Index page</title>
    <meta charset="UTF-8">
</head>
<body>
    <nav>
        <ul>
            <li><a href="nameJsp.jsp">Hello FIO and database</a></li>
            <li><a href="/hello">Static hello</a></li>
            <li><a href="/hello-name">Dynamic hello</a></li>
            <li><a href="houseJsp.jsp">Add house</a></li>
            <li><a href="/person-house  ">Add person to house</a></li>
            <li><a href="/resident">Show house residents</a></li>
        </ul>
    </nav>
</body>
</html>
