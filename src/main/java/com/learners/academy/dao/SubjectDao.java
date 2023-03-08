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
    Subject dbSubject = session.find(Subject.class, subject.getId());
    dbSubject.setName(subject.getName());
    dbSubject.setLevel(subject.getLevel());
    session.update(dbSubject);
    Database.closeSession(session);
    return dbSubject;
  }

  public Boolean delete(Long id) {
    Session session = Database.openSession(true);
    Subject subject = session.find(Subject.class, id);
    session.delete(subject);
    Database.closeSession(session);
    return true;
  }

  public List<Subject> findAll() {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from Subject");
    List<Subject> clazzes = typedQuery.getResultList();
    Database.closeSession(session);
    return clazzes;
  }

  public Subject findById(Long id) {
    Session session = Database.openSession(false);
    Subject subject = session.find(Subject.class, id);
    Database.closeSession(session);
    return subject;
  }
}
