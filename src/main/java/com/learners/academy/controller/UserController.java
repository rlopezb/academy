package com.learners.academy.controller;

import com.learners.academy.entity.User;
import com.learners.academy.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/UserController")
public class UserController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Get user(s)
    RequestDispatcher requestDispatcher;
    if (request.getParameter("id") != null) {
      try {
        Long id = Long.valueOf(request.getParameter("id"));
        UserService userService = new UserService();
        User user = userService.findById(id);
        request.setAttribute("user", user);
        if (user == null) {
          request.setAttribute("status", "info");
          request.setAttribute("message", "User with id " + id + " not found");
        } else {
          request.setAttribute("status", "ok");
          request.setAttribute("message", "User with id " + id + " found");
        }
        requestDispatcher = request.getRequestDispatcher("viewUser.jsp");
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("message", "Cannot find user: " + ex);
        requestDispatcher = request.getRequestDispatcher("error.jsp");
      }
    } else {
      try {
        UserService userService = new UserService();
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "List of " + users.size() + " users retrieved");
        requestDispatcher = request.getRequestDispatcher("viewUsers.jsp");
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("error", "Cannot list all users: " + ex);
        requestDispatcher = request.getRequestDispatcher("error.jsp");
      }
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Add new student
    String action = request.getParameter("action");
    switch (action) {
      case "delete":
        delete(request, response);
        break;
      case "add":
        add(request, response);
        break;
      case "modify":
        modify(request, response);
        break;
    }
  }

  private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Add new user
    RequestDispatcher requestDispatcher;
    try {
      String name = request.getParameter("name");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      String login = request.getParameter("login");
      String password = request.getParameter("password");
      User user = new User();
      user.setName(name);
      user.setLastName(lastName);
      user.setEmail(email);
      user.setPhone(phone);
      user.setLogin(login);
      user.setPassword(password);

      UserService userService = new UserService();
      user = userService.add(user);
      request.setAttribute("user", user);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "New user added with id " + user.getId());
      requestDispatcher = request.getRequestDispatcher("viewUser.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot add user: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }


  private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify user
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      String name = request.getParameter("name");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      String login = request.getParameter("login");
      String password = request.getParameter("password");
      User user = new User();
      user.setId(id);
      user.setName(name);
      user.setLastName(lastName);
      user.setEmail(email);
      user.setPhone(phone);
      user.setLogin(login);
      user.setPassword(password);

      UserService userService = new UserService();
      user = userService.update(user);
      request.setAttribute("user", user);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "User with id " + user.getId() + " updated");
      requestDispatcher = request.getRequestDispatcher("viewUser.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find user: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }


  private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Delete user
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      UserService userService = new UserService();
      userService.delete(id);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "User with id " + id + " deleted");
      request.setAttribute("user", null);
      requestDispatcher = request.getRequestDispatcher("viewUser.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot delete user: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }
}
