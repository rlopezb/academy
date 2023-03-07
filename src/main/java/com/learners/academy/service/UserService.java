package com.learners.academy.service;

import com.learners.academy.dao.UserDao;
import com.learners.academy.entity.User;

import java.util.List;

public class UserService {
  private UserDao userDao = new UserDao();

  public User add(User user) {
    return userDao.add(user);
  }

  public User update(User user) {
    return userDao.update(user);
  }

  public Boolean delete(Long id) {
    return userDao.delete(id);

  }

  public List<User> findAll() {
    return userDao.findAll();

  }

  public User findById(Long id) {
    return userDao.findById(id);
  }
  public User findByLoginAndPassword(String login, String password) {
    return userDao.findByLoginAndPassword(login, password);
  }
}