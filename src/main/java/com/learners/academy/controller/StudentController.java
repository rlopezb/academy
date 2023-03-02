package com.learners.academy.controller;

import com.learners.academy.entity.Student;
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
      Long id = Long.valueOf(request.getParameter("id"));
      try {
        StudentService studentService = new StudentService();
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        request.setAttribute("message", "Student wit id " + id + " found");
        requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
      } catch (Exception ex) {
        request.setAttribute("error", "Cannot find student by id " + id + ": " + ex);
        requestDispatcher = request.getRequestDispatcher("error.jsp");
      }
    } else {
      try {
        StudentService studentService = new StudentService();
        List<Student> students = studentService.findAll();
        request.setAttribute("students", students);
        request.setAttribute("message", "List of " + students.size() + " students retrieved");
        requestDispatcher = request.getRequestDispatcher("viewStudentList.jsp");
      } catch (Exception ex) {
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
    String name = request.getParameter("name");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    Student student = new Student();
    student.setName(name);
    student.setLastName(lastName);
    student.setEmail(email);
    student.setPhone(phone);
    RequestDispatcher requestDispatcher;
    try {
      StudentService studentService = new StudentService();
      student = studentService.add(student);
      request.setAttribute("student", student);
      request.setAttribute("message", "New student added with id " + student.getId());
      requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("error", "Cannot add student: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify student
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
    RequestDispatcher requestDispatcher;
    try {
      StudentService studentService = new StudentService();
      student = studentService.update(student);
      request.setAttribute("student", student);
      request.setAttribute("message", "Student with id " + student.getId() + " updated");
      requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("error", "Cannot find student by id " + id + ": " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Delete student
    Long id = Long.valueOf(request.getParameter("id"));
    RequestDispatcher requestDispatcher;
    try {
      StudentService studentService = new StudentService();
      studentService.delete(id);
      request.setAttribute("message", "Student with id " + id + " deleted");
      request.setAttribute("student", null);
      requestDispatcher = request.getRequestDispatcher("viewStudent.jsp");
    } catch (Exception ex) {
      request.setAttribute("error", "Cannot delete student with id " + id + ": " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }
}
