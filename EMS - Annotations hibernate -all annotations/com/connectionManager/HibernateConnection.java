package com.connectionManager;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
    private static SessionFactory factory = null;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Unable to create session factory. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
