package com.ideas2it.ems.connectionManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
*  <p>
* This class manages the Hibernate SessionFactory, providing access to the database
* for Hibernate-based operations throughout the application.
* Building hibernate connection here
* </p>
*
* @author Dharani G
* 
*/
public class HibernateConnection {
    private static SessionFactory factory = null;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Unable to create session factory. " + e);
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
