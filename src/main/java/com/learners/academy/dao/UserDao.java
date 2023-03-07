package com.learners.academy.dao;

import com.learners.academy.entity.User;
import com.learners.academy.resource.Database;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {

  public User add(User user) {
    Session session = Database.openSession(true);
    session.save(user);
    Database.closeSession(session);
    return user;
  }

  public User update(User user) {
    Session session = Database.openSession(true);
    session.update(user);
    Database.closeSession(session);
    return user;
  }

  public Boolean delete(Long id) {
    Session session = Database.openSession(true);
    User user = session.find(User.class, id);
    session.delete(user);
    Database.closeSession(session);
    return true;
  }

  public List<User> findAll() {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from User");
    List<User> users = typedQuery.getResultList();
    Database.closeSession(session);
    return users;
  }

  public User findById(Long id) {
    Session session = Database.openSession(false);
    User user = session.find(User.class, id);
    Database.closeSession(session);
    return user;
  }

  public User findByLoginAndPassword(String login, String password) {
    Session session = Database.openSession(false);
    TypedQuery typedQuery = session.createQuery("from User where login=:login and password=:password");
    typedQuery.setParameter("login", login);
    typedQuery.setParameter("password", password);
    List<User> users = typedQuery.getResultList();
    User user = null;
    if (!users.isEmpty()) {
      user = users.get(0);
    }
    Database.closeSession(session);
    return user;
  }

}
