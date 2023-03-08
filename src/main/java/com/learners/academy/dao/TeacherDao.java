package com.learners.academy.dao;

import com.learners.academy.entity.Teacher;
import com.learners.academy.resource.Database;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class TeacherDao {

  public Teacher add(Teacher teacher) {
    Session session = Database.openSession(true);
    session.save(teacher);
    Database.closeSession(session);
    return teacher;
  }

  public Teacher update(Teacher teacher) {
    Session session = Database.openSession(true);
    Teacher dbTeacher = session.find(Teacher.class, teacher.getId());
    dbTeacher.setName(teacher.getName());
    dbTeacher.setLastName(teacher.getLastName());
    dbTeacher.setEmail(teacher.getEmail());
    dbTeacher.setPhone(teacher.getPhone());
    session.update(dbTeacher);
    Database.closeSession(session);
    return dbTeacher;
  }

  public Boolean delete(Long id) {
    Session session = Database.openSession(true);
    Teacher teacher = session.find(Teacher.class, id);
    session.delete(teacher);
    Database.closeSession(session);
    return true;
  }

  public List<Teacher> findAll() {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from Teacher");
    List<Teacher> clazzes = typedQuery.getResultList();
    Database.closeSession(session);
    return clazzes;
  }

  public Teacher findById(Long id) {
    Session session = Database.openSession(false);
    Teacher teacher = session.find(Teacher.class, id);
    Database.closeSession(session);
    return teacher;
  }
}
