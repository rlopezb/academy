package com.learners.academy.service;

import com.learners.academy.dao.SubjectDao;
import com.learners.academy.entity.Subject;

import java.util.List;

public class SubjectService {
  private SubjectDao subjectDao = new SubjectDao();

  public Subject add(Subject subject) {
    return subjectDao.add(subject);
  }

  public Subject update(Subject subject) {
    return subjectDao.update(subject);
  }

  public Subject updateClazz(Subject subject) {
    return subjectDao.updateClazz(subject);
  }
  public Subject updateTeacher(Subject subject) {
    return subjectDao.updateTeacher(subject);
  }

  public Boolean delete(Long id) {
    return subjectDao.delete(id);

  }

  public List<Subject> findAll() {
    return subjectDao.findAll();

  }

  public Subject findById(Long id) {
    return subjectDao.findById(id);

  }
}