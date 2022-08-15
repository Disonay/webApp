package servlets;

import org.hibernate.SessionFactory;
import repository.HibernateSessionFactoryUtil;
import repository.house.HibernatePostgresRepositoryHouse;
import repository.house.RepositoryHouse;
import repository.person.HibernatePostgresRepositoryPerson;
import repository.person.RepositoryPerson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/person-house")
public class PersonToHouseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sf = HibernateSessionFactoryUtil.getSessionFactory();
        try (RepositoryHouse repositoryHouse = new HibernatePostgresRepositoryHouse();
             RepositoryPerson repositoryPerson = new HibernatePostgresRepositoryPerson()) {
            req.setAttribute("houses", repositoryHouse.getAll());
            req.setAttribute("persons", repositoryPerson.getAll());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        RequestDispatcher rq = req.getRequestDispatcher("/personToHouseJsp.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int houseId = Integer.parseInt(req.getParameter("house"));
        List<Integer> personsId = Arrays.stream(req.getParameterValues("persons")).map(Integer::parseInt).toList();

        try (RepositoryHouse repositoryHouse = new HibernatePostgresRepositoryHouse();
             RepositoryPerson repositoryPerson = new HibernatePostgresRepositoryPerson()) {
            for (Integer personId : personsId) {
                repositoryHouse.addPersonToHouse(houseId, repositoryPerson.get(personId));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
