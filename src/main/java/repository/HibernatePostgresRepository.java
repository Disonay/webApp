package repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.entities.UsersEntity;

import java.sql.SQLException;
import java.util.List;

public class HibernatePostgresRepository implements Repository{
    private final SessionFactory sf;

    public HibernatePostgresRepository() {
        sf = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public void save(UsersEntity user) throws SQLException {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.persist(user);
            transaction.commit();
        }
    }

    @Override
    public UsersEntity get(Integer id) throws SQLException {
        try (Session session = sf.openSession()) {
            return session.get(UsersEntity.class, id);
        }
    }

    @Override
    public List<UsersEntity> getAll() throws SQLException {
        try (Session session = sf.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UsersEntity> criteria = builder.createQuery(UsersEntity.class);
            criteria.from(UsersEntity.class);

            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void close()  {

    }
}
