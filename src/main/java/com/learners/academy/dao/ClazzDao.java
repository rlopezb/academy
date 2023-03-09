package com.learners.academy.dao;

import com.learners.academy.entity.Clazz;
import com.learners.academy.entity.Student;
import com.learners.academy.entity.Subject;
import com.learners.academy.entity.Teacher;
import com.learners.academy.resource.Database;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class ClazzDao {

  public Clazz add(Clazz clazz) {
    Session session = Database.openSession(true);
    session.save(clazz);
    Database.closeSession(session);
    return clazz;
  }

  public Clazz update(Clazz clazz) {
    Session session = Database.openSession(true);
    Clazz dbClazz = session.find(Clazz.class, clazz.getId());
    dbClazz.setLine(clazz.getLine());
    dbClazz.setLevel(clazz.getLevel());
    session.update(dbClazz);
    Database.closeSession(session);
    return dbClazz;
  }

  public Boolean delete(Long id) {
    Session session = Database.openSession(true);
    Clazz clazz = session.find(Clazz.class, id);
    session.delete(clazz);
    Database.closeSession(session);
    return true;
  }

  public List<Clazz> findAll() {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from Clazz");
    List<Clazz> clazzes = typedQuery.getResultList();
    Database.closeSession(session);
    return clazzes;
  }

  public Clazz findById(Long id) {
    Session session = Database.openSession(false);
    Clazz clazz = session.find(Clazz.class, id);
    Database.closeSession(session);
    return clazz;
  }
  public Clazz eagerFindById(Long id) {
    Session session = Database.openSession(false);
    Clazz clazz = session.find(Clazz.class, id);
    TypedQuery studentsTypedQuery = session.createQuery("from Student where clazz=:clazz");
    studentsTypedQuery.setParameter("clazz", clazz);
    List<Student> students = studentsTypedQuery.getResultList();
    clazz.setStudents(students);
    TypedQuery subjectsTypedQuery = session.createQuery("from Subject where clazz=:clazz");
    subjectsTypedQuery.setParameter("clazz", clazz);
    List<Subject> subjects = subjectsTypedQuery.getResultList();
    for(Subject subject:subjects){

    }
    clazz.setSubjects(subjects);
    Database.closeSession(session);
    return clazz;
  }
}
