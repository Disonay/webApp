package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository.HibernatePostgresRepository;
import repository.JDBCpostgresRepository;
import repository.Repository;
import repository.entities.User;
import repository.entities.UsersEntity;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloFioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("text/html");

        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        String middleName = (String) session.getAttribute("middleName");

        PrintWriter pw = resp.getWriter();
        try (Repository repository = new HibernatePostgresRepository()) {
            for (UsersEntity user : repository.getAll()) {
                pw.println("<p>" + user + "</p>");
            }
            UsersEntity newUser = new UsersEntity(name, surname, middleName);
            repository.save(newUser);
            pw.println("<h1>Hello to new user: " + newUser + "!</h1>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
