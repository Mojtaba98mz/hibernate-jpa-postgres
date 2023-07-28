package org.example.hibernate.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static public SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = initSessionFactory();
        return sessionFactory;
    }

    static public void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }

    static private SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }
}