package com.learners.academy.dao;

import com.learners.academy.entity.Subject;
import com.learners.academy.resource.Database;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubjectDao {

  public Subject add(Subject subject) {
    Session session = Database.openSession(true);
    session.save(subject);
    Database.closeSession(session);
    return subject;
  }

  public Subject update(Subject subject) {
    Session session = Database.openSession(true);
    session.update(subject);
    Database.closeSession(session);
    return subject;
  }

  public Boolean delete(Subject subject) {
    Session session = Database.openSession(true);
    session.delete(subject);
    Database.closeSession(session);
    return true;
  }

  public List<Subject> findAll() {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from Subject");
    List<Subject> classes = typedQuery.getResultList();
    Database.closeSession(session);
    return classes;
  }

  public Subject findById(Long id) {
    Session session = Database.openSession(false);
    Subject subject = session.find(Subject.class, id);
    Database.closeSession(session);
    return subject;
  }
}
