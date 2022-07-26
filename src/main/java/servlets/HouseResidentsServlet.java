package servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.HibernateSessionFactoryUtil;
import repository.entities.HouseEntity;
import repository.house.HibernatePostgresRepositoryHouse;
import repository.house.RepositoryHouse;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/resident")
public class HouseResidentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (RepositoryHouse repositoryHouse = new HibernatePostgresRepositoryHouse()) {
            req.setAttribute("houses", repositoryHouse.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        RequestDispatcher rq = req.getRequestDispatcher("/houseResidentsJsp.jsp");
        rq.forward(req, resp);
    }
}
