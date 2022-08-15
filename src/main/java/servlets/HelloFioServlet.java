package servlets;

import repository.JDBCpostgresRepository;
import repository.Repository;
import repository.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloFioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        resp.setContentType("text/html");

        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        String middleName = (String) session.getAttribute("middleName");

        PrintWriter pw = resp.getWriter();
        try (Repository repository = new JDBCpostgresRepository()) {
            for (User user : repository.getAll()) {
                pw.println("<p>" + user + "</p>");
            }
            pw.println("<h1>Hello to new user: " + repository.save(new User(name, surname, middleName)) + "!</h1>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
