package data.dao.house;

import data.entities.HouseEntity;
import data.entities.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public class HibernateHouseDAO implements HouseDAO {
    private final SessionFactory sf;

    public HibernateHouseDAO(@Autowired SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public HouseEntity save(HouseEntity house)  {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.save(house);
            transaction.commit();
            return house;
        }
    }

    @Override
    public HouseEntity findById(Integer id)  {
        try (Session session = sf.openSession()) {
            return session.get(HouseEntity.class, id);
        }
    }

    @Override
    public List<HouseEntity> getAll() {
        try (Session session1 = sf.openSession()) {
            CriteriaBuilder builder = session1.getCriteriaBuilder();
            CriteriaQuery<HouseEntity> criteria = builder.createQuery(HouseEntity.class);
            criteria.from(HouseEntity.class);

            return session1.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void addPersonToHouse(int houseId, PersonEntity person) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            HouseEntity house = session.get(HouseEntity.class, houseId);
            person.setHouse(house);
            house.addPerson(person);

            transaction.commit();
        }
    }
}
