package repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactoryUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
