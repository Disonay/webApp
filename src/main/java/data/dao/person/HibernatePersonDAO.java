package data.dao.person;

import data.entities.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HibernatePersonDAO implements PersonDAO {
    private final SessionFactory sf;

    public HibernatePersonDAO(@Autowired SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public PersonEntity save(PersonEntity person) throws SQLException {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Integer id = (Integer) session.save(person);
            transaction.commit();
            return new PersonEntity(id, person.getName(), person.getSurname(), person.getMiddleName());
        }
    }

    @Override
    public PersonEntity findById(Integer id) throws SQLException {
        try (Session session = sf.openSession()) {
            return session.get(PersonEntity.class, id);
        }
    }

    @Override
    public List<PersonEntity> getAll() throws SQLException {
        try (Session session = sf.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<PersonEntity> criteria = builder.createQuery(PersonEntity.class);
            criteria.from(PersonEntity.class);

            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void close() {

    }
}
