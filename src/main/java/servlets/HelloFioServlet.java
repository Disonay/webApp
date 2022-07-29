package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.entities.PersonEntity;
import data.dao.person.HibernatePostgresRepositoryPerson;
import data.dao.person.RepositoryPerson;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-fio")
public class HelloFioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        HttpSession session = req.getSession();
        resp.setContentType ("text/html; charset=UTF-8");

        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        String middleName = (String) session.getAttribute("middleName");

        PrintWriter pw = resp.getWriter();
        try (RepositoryPerson repositoryPerson = new HibernatePostgresRepositoryPerson()) {
            for (PersonEntity person : repositoryPerson.getAll()) {
                pw.println("<p>" + person + "</p>");
            }
            PersonEntity newPerson = repositoryPerson.save(new PersonEntity(name, surname, middleName));
            pw.println("<h1>Hello to new user: " + newPerson + "!</h1>");

            session.setAttribute("person", newPerson);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
