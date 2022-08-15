package servlets;

import repository.entities.HouseEntity;
import repository.house.HibernatePostgresRepositoryHouse;
import repository.house.RepositoryHouse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/house")
public class AddHouseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF8");

        String city = req.getParameter("city");
        String street = req.getParameter("street");
        int number = Integer.parseInt(req.getParameter("number"));

        try (RepositoryHouse repositoryHouse = new HibernatePostgresRepositoryHouse()) {
            repositoryHouse.save(new HouseEntity(city, street, number));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
