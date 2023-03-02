package com.learners.academy.dao;

import com.learners.academy.resource.Database;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class ClassDao {

  public Class add(Class clazz) {
    Session session = Database.openSession(true);
    session.save(clazz);
    Database.closeSession(session);
    return clazz;
  }

  public Class update(Class clazz) {
    Session session = Database.openSession(true);
    session.update(clazz);
    Database.closeSession(session);
    return clazz;
  }

  public Boolean delete(Class clazz) {
    Session session = Database.openSession(true);
    session.delete(clazz);
    Database.closeSession(session);
    return true;
  }

  public List<Class> findAll() {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from Class");
    List<Class> classes = typedQuery.getResultList();
    Database.closeSession(session);
    return classes;
  }

  public Class findById(Long id) {
    Session session = Database.openSession(false);
    Class clazz = session.find(Class.class, id);
    Database.closeSession(session);
    return clazz;
  }
}
