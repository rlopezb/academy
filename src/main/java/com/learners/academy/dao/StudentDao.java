package com.learners.academy.dao;

import com.learners.academy.entity.Clazz;
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
    Student dbStudent = session.find(Student.class, student.getId());
    dbStudent.setName(student.getName());
    dbStudent.setLastName(student.getLastName());
    dbStudent.setEmail(student.getEmail());
    dbStudent.setPhone(student.getPhone());
    session.update(dbStudent);
    Database.closeSession(session);
    return dbStudent;
  }

  public Student updateClazz(Student student){
    Session session = Database.openSession(true);
    Student dbStudent = session.find(Student.class, student.getId());
    dbStudent.setClazz(student.getClazz());
    session.update(dbStudent);
    Database.closeSession(session);
    return dbStudent;
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
    List<Student> students = typedQuery.getResultList();
    Database.closeSession(session);
    return students;
  }

  public Student findById(Long id) {
    Session session = Database.openSession(false);
    Student student = session.find(Student.class, id);
    Database.closeSession(session);
    return student;
  }
}
