package data.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            final String USERNAME_PROPERTY = "USERNAME";
            final String PASSWORD_PROPERTY = "PASSWORD";
            final String URL_PROPERTY = "URL";

            Configuration cfg = new Configuration().configure();

            cfg.setProperty("hibernate.connection.url", System.getProperty(URL_PROPERTY));
            cfg.setProperty("hibernate.connection.username", System.getProperty(USERNAME_PROPERTY));
            cfg.setProperty("hibernate.connection.password", System.getProperty(PASSWORD_PROPERTY));

            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
