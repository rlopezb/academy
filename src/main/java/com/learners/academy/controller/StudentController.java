package com.learners.academy.controller;

import com.learners.academy.entity.Clazz;
import com.learners.academy.entity.Student;
import com.learners.academy.service.ClazzService;
import com.learners.academy.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentController", value = "/StudentController")
public class StudentController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Get student(s)
    RequestDispatcher requestDispatcher;
    if (request.getParameter("id") != null) {
      try {
        Long id = Long.valueOf(request.getParameter("id"));
        StudentService studentService = new StudentService();
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        if (student == null) {
          request.setAttribute("status", "info");
          request.setAttribute("message", "Student with id " + id + " not found");
        } else {
          request.setAttribute("status", "ok");
          request.setAttribute("message", "Student with id " + id + " found");
        }
        requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("message", "Cannot find student: " + ex);
        requestDispatcher = request.getRequestDispatcher("error.jsp");
      }
    } else {
      try {
        StudentService studentService = new StudentService();
        List<Student> students = studentService.findAll();
        request.setAttribute("students", students);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "List of " + students.size() + " students retrieved");
        requestDispatcher = request.getRequestDispatcher("viewStudents.jsp");
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("error", "Cannot list all students: " + ex);
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
      case "assignClazz":
        assignClazz(request, response);
        break;
      case "modifyClazz":
        modifyClazz(request, response);
        break;
    }
  }

  private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher;
    try {
      String name = request.getParameter("name");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      Student student = new Student();
      student.setName(name);
      student.setLastName(lastName);
      student.setEmail(email);
      student.setPhone(phone);

      StudentService studentService = new StudentService();
      student = studentService.add(student);
      request.setAttribute("student", student);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "New student added with id " + student.getId());
      requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot add student: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify student
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      String name = request.getParameter("name");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      Student student = new Student();
      student.setId(id);
      student.setName(name);
      student.setLastName(lastName);
      student.setEmail(email);
      student.setPhone(phone);

      StudentService studentService = new StudentService();
      student = studentService.update(student);
      request.setAttribute("student", student);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "Student with id " + student.getId() + " updated");
      requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find student: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void modifyClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify student
    RequestDispatcher requestDispatcher;
    try {
      Long studentId = Long.valueOf(request.getParameter("studentId"));
      StudentService studentService = new StudentService();
      Student student = studentService.findById(studentId);

      if (request.getParameter("clazzId").equals("")) {
        student.setClazz(null);
        studentService.updateClazz(student);
        request.setAttribute("student", student);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "Student with id " + student.getId() + " not assigned to any class");
      } else {
        Long clazzId = Long.valueOf(request.getParameter("clazzId"));
        ClazzService clazzService = new ClazzService();
        Clazz clazz = clazzService.findById(clazzId);
        student.setClazz(clazz);
        studentService.updateClazz(student);
        request.setAttribute("student", student);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "Student with id " + student.getId() + " assigned to class with id " + clazz.getId());
      }
      requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find student: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void assignClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify student
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      StudentService studentService = new StudentService();
      Student student = studentService.findById(id);
      ClazzService clazzService = new ClazzService();
      List<Clazz> clazzes = clazzService.findAll();
      request.setAttribute("clazzes", clazzes);
      request.setAttribute("student", student);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "List of " + clazzes.size() + " possible classes for student with id " + student.getId());
      requestDispatcher = request.getRequestDispatcher("assignClazzToStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find student: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Delete student
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      StudentService studentService = new StudentService();
      studentService.delete(id);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "Student with id " + id + " deleted");
      request.setAttribute("student", null);
      requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot delete student: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }
}
