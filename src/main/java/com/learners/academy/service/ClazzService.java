package com.learners.academy.service;

import com.learners.academy.dao.ClazzDao;
import com.learners.academy.entity.Clazz;

import java.util.List;

public class ClazzService {
  private ClazzDao clazzDao = new ClazzDao();

  public Clazz add(Clazz clazz) {
    return clazzDao.add(clazz);
  }

  public Clazz update(Clazz clazz) {
    return clazzDao.update(clazz);
  }

  public Boolean delete(Long id) {
    return clazzDao.delete(id);

  }

  public List<Clazz> findAll() {
    return clazzDao.findAll();

  }

  public Clazz findById(Long id) {
    return clazzDao.findById(id);

  }
  public Clazz eagerFindById(Long id) {
    return clazzDao.eagerFindById(id);

  }
}