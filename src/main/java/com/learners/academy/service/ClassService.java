package com.learners.academy.service;

import com.learners.academy.dao.ClassDao;

import java.util.List;

public class ClassService {
  private ClassDao clazzDao = new ClassDao();

  public Class add(Class clazz) {
    return clazzDao.add(clazz);
  }

  public Class update(Class clazz) {
    return clazzDao.update(clazz);
  }

  public Boolean delete(Class clazz) {
    return clazzDao.delete(clazz);

  }

  public List<Class> findAll() {
    return clazzDao.findAll();

  }

  public Class findById(Long id) {
    return clazzDao.findById(id);

  }
}