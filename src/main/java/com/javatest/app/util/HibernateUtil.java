package com.javatest.app.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * class for work with the Hibernate Session
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
public class HibernateUtil {
    public static SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Method for build Hibernate session
     * @return Hibernate session
     */
    protected static SessionFactory buildSessionFactory() {
        /**
         * A SessionFactory is set up once for an application!
         */
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() /** configures settings from hibernate.cfg.xml */
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            /**
             * The registry would be destroyed by the SessionFactory, but if it was trouble building the SessionFactory
             * then destroy it manually.
             */
            StandardServiceRegistryBuilder.destroy( registry );

            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
        return sessionFactory;
    }


    /**
     * Method for return Hibernate session
     * @return Hibernate session
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Close caches and connection pools
     */
    public static void shutdown() {
        getSessionFactory().close();
    }

}
