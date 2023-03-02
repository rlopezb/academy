package com.learners.academy.resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Database {
  private static final SessionFactory sessionFactory;
  static {
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    sessionFactory = configuration.buildSessionFactory();
  }

  public static Session openSession(Boolean atomic) {
    Session session = sessionFactory.openSession();
    if (atomic) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
    }
    return session;
  }

  public static void closeSession(Session session) {
    if (session.getTransaction().isActive()) {
      Transaction transaction = session.getTransaction();
      transaction.commit();
    }
    session.close();
  }
}
