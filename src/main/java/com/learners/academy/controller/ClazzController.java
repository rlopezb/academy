package com.learners.academy.controller;

import com.learners.academy.entity.Clazz;
import com.learners.academy.service.ClazzService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClazzController", value = "/ClazzController")
public class ClazzController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Get clazz(s)
    RequestDispatcher requestDispatcher;
    if (request.getParameter("id") != null) {
      try {
        String action = request.getParameter("action");
        Clazz clazz;
        Long id = Long.valueOf(request.getParameter("id"));
        if (action != null && action.equals("report")) {
          requestDispatcher = request.getRequestDispatcher("reportClazz.jsp");
          ClazzService clazzService = new ClazzService();
          clazz = clazzService.eagerFindById(id);
        } else {
          requestDispatcher = request.getRequestDispatcher("viewClazz.jsp");
          ClazzService clazzService = new ClazzService();
          clazz = clazzService.findById(id);
        }
        request.setAttribute("clazz", clazz);
        if (clazz == null) {
          request.setAttribute("status", "info");
          request.setAttribute("message", "Clazz with id " + id + " not found");
        } else {
          request.setAttribute("status", "ok");
          request.setAttribute("message", "Clazz with id " + id + " found");
        }
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("message", "Cannot find clazz: " + ex);
        requestDispatcher = request.getRequestDispatcher("error.jsp");
      }
    } else {
      try {
        ClazzService clazzService = new ClazzService();
        List<Clazz> clazzes = clazzService.findAll();
        request.setAttribute("clazzes", clazzes);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "List of " + clazzes.size() + " clazzes retrieved");
        requestDispatcher = request.getRequestDispatcher("viewClazzes.jsp");
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("error", "Cannot list all clazzes: " + ex);
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
    //Add new clazz
    RequestDispatcher requestDispatcher;
    try {
      String line = request.getParameter("line");
      String level = request.getParameter("level");
      Clazz clazz = new Clazz();
      clazz.setLine(line);
      clazz.setLevel(level);

      ClazzService clazzService = new ClazzService();
      clazz = clazzService.add(clazz);
      request.setAttribute("clazz", clazz);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "New clazz added with id " + clazz.getId());
      requestDispatcher = request.getRequestDispatcher("viewClazz.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot add clazz: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }


  private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify clazz
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      String line = request.getParameter("line");
      String level = request.getParameter("level");
      Clazz clazz = new Clazz();
      clazz.setId(id);
      clazz.setLine(line);
      clazz.setLevel(level);

      ClazzService clazzService = new ClazzService();
      clazz = clazzService.update(clazz);
      request.setAttribute("clazz", clazz);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "Clazz with id " + clazz.getId() + " updated");
      requestDispatcher = request.getRequestDispatcher("viewClazz.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find clazz: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Delete clazz
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      ClazzService clazzService = new ClazzService();
      clazzService.delete(id);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "Clazz with id " + id + " deleted");
      request.setAttribute("clazz", null);
      requestDispatcher = request.getRequestDispatcher("viewClazz.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot delete clazz: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }
}
