package com.learners.academy.service;

import com.learners.academy.dao.StudentDao;
import com.learners.academy.entity.Student;

import java.util.List;

public class StudentService {
  private StudentDao studentDao = new StudentDao();

  public Student add(Student student) {
    return studentDao.add(student);
  }

  public Student update(Student student) {
    return studentDao.update(student);
  }
  public Student updateClazz(Student student) {
    return studentDao.updateClazz(student);
  }

  public Boolean delete(Long id) {
    return studentDao.delete(id);

  }

  public List<Student> findAll() {
    return studentDao.findAll();

  }

  public Student findById(Long id) {
    return studentDao.findById(id);

  }
}