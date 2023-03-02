package com.learners.academy.service;

import com.learners.academy.dao.TeacherDao;
import com.learners.academy.entity.Teacher;

import java.util.List;

public class TeacherService {
  private TeacherDao teacherDao = new TeacherDao();

  public Teacher add(Teacher teacher) {
    return teacherDao.add(teacher);
  }

  public Teacher update(Teacher teacher) {
    return teacherDao.update(teacher);
  }

  public Boolean delete(Long id) {
    return teacherDao.delete(id);

  }

  public List<Teacher> findAll() {
    return teacherDao.findAll();

  }

  public Teacher findById(Long id) {
    return teacherDao.findById(id);

  }
}