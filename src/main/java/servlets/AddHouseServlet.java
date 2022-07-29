package servlets;

import data.entities.HouseEntity;
import data.dao.house.HibernatePostgresRepositoryHouse;
import data.dao.house.RepositoryHouse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/house")
public class AddHouseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

        String city = req.getParameter("city");
        String street = req.getParameter("street");
        int number = Integer.parseInt(req.getParameter("number"));

        PrintWriter pw = resp.getWriter();
        try (RepositoryHouse repositoryHouse = new HibernatePostgresRepositoryHouse()) {
            repositoryHouse.save(new HouseEntity(city, street, number));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
