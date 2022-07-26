package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType ("text/html; charset=UTF-8");
        try (PrintWriter pw = resp.getWriter()) {
            pw.write("<h1>Hello, Сергей<h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF8");
        resp.setContentType ("text/html; charset=UTF-8");
        try (PrintWriter pw = resp.getWriter()) {
            pw.write("<h1>Hello, " + req.getParameter("name") + "<h1>");
        }
    }
}
