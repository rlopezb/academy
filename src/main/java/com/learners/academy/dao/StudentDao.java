package com.learners.academy.dao;

import com.learners.academy.entity.Student;
import com.learners.academy.resource.Database;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class StudentDao {

  public Student add(Student student) {
    Session session = Database.openSession(true);
    session.save(student);
    Database.closeSession(session);
    return student;
  }

  public Student update(Student student) {
    Session session = Database.openSession(true);
    session.update(student);
    Database.closeSession(session);
    return student;
  }

  public Boolean delete(Long id) {
    Session session = Database.openSession(true);
    Student student = session.find(Student.class,id);
    session.delete(student);
    Database.closeSession(session);
    return true;
  }

  public List<Student> findAll() {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from Student");
    List<Student> clazzes = typedQuery.getResultList();
    Database.closeSession(session);
    return clazzes;
  }

  public Student findById(Long id) {
    Session session = Database.openSession(false);
    Student student = session.find(Student.class, id);
    Database.closeSession(session);
    return student;
  }
}
