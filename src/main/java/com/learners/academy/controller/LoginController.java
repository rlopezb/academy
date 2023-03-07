package com.learners.academy.controller;

import com.learners.academy.entity.User;
import com.learners.academy.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = null;
    try {
      String login = request.getParameter("login");
      String password = request.getParameter("password");
      UserService userService = new UserService();
      User user = userService.findByLoginAndPassword(login, password);
      if (user == null) {
        HttpSession session = request.getSession(false);
        if (session != null) {
          session.invalidate();
        }
        request.setAttribute("status", "warning");
        request.setAttribute("message", "Invalid user credentials");
        requestDispatcher = request.getRequestDispatcher("error.jsp");
        requestDispatcher.include(request, response);
      } else {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request, response);
      }

    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot login user: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
      requestDispatcher.include(request, response);
    }
    response.setContentType("text/html");
  }

}
