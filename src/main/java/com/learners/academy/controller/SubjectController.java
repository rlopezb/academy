package com.learners.academy.controller;

import com.learners.academy.entity.Clazz;
import com.learners.academy.entity.Subject;
import com.learners.academy.entity.Teacher;
import com.learners.academy.service.ClazzService;
import com.learners.academy.service.SubjectService;
import com.learners.academy.service.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SubjectController", value = "/SubjectController")
public class SubjectController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Get subject(s)
    RequestDispatcher requestDispatcher;
    if (request.getParameter("id") != null) {
      Long id = Long.valueOf(request.getParameter("id"));
      try {
        SubjectService subjectService = new SubjectService();
        Subject subject = subjectService.findById(id);
        request.setAttribute("subject", subject);
        if (subject == null) {
          request.setAttribute("status", "info");
          request.setAttribute("message", "Subject with id " + id + " not found");
        } else {
          request.setAttribute("status", "ok");
          request.setAttribute("message", "Subject with id " + id + " found");
        }
        requestDispatcher = request.getRequestDispatcher("viewSubject.jsp");
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("message", "Cannot find subject by id " + id + ": " + ex);
        requestDispatcher = request.getRequestDispatcher("error.jsp");
      }
    } else {
      try {
        SubjectService subjectService = new SubjectService();
        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("subjects", subjects);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "List of " + subjects.size() + " subjects retrieved");
        requestDispatcher = request.getRequestDispatcher("viewSubjects.jsp");
      } catch (Exception ex) {
        request.setAttribute("status", "error");
        request.setAttribute("error", "Cannot list all subjects: " + ex);
        requestDispatcher = request.getRequestDispatcher("error.jsp");
      }
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Add new subject
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
      case "assignTeacher":
        assignTeacher(request, response);
        break;
      case "modifyTeacher":
        modifyTeacher(request, response);
        break;
    }
  }


  private void modifyClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify subject
    RequestDispatcher requestDispatcher;
    try {
      Long subjectId = Long.valueOf(request.getParameter("subjectId"));
      SubjectService subjectService = new SubjectService();
      Subject subject = subjectService.findById(subjectId);

      if (request.getParameter("clazzId").equals("")) {
        subject.setClazz(null);
        subjectService.updateClazz(subject);
        request.setAttribute("subject", subject);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "Subject with id " + subject.getId() + " not assigned to any class");
      } else {
        Long clazzId = Long.valueOf(request.getParameter("clazzId"));
        ClazzService clazzService = new ClazzService();
        Clazz clazz = clazzService.findById(clazzId);
        subject.setClazz(clazz);
        subjectService.updateClazz(subject);
        request.setAttribute("subject", subject);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "Subject with id " + subject.getId() + " assigned to class with id " + clazz.getId());
      }
      requestDispatcher = request.getRequestDispatcher("viewSubject.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find subject: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void assignClazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify subject
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      SubjectService subjectService = new SubjectService();
      Subject subject = subjectService.findById(id);
      ClazzService clazzService = new ClazzService();
      List<Clazz> clazzes = clazzService.findAll();
      request.setAttribute("clazzes", clazzes);
      request.setAttribute("subject", subject);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "List of " + clazzes.size() + " possible classes for subject with id " + subject.getId());
      requestDispatcher = request.getRequestDispatcher("assignClazzToSubject.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find subject: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void modifyTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify subject
    RequestDispatcher requestDispatcher;
    try {
      Long subjectId = Long.valueOf(request.getParameter("subjectId"));
      SubjectService subjectService = new SubjectService();
      Subject subject = subjectService.findById(subjectId);

      if (request.getParameter("teacherId").equals("")) {
        subject.setTeacher(null);
        subjectService.updateTeacher(subject);
        request.setAttribute("subject", subject);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "Subject with id " + subject.getId() + " not assigned to any class");
      } else {
        Long teacherId = Long.valueOf(request.getParameter("teacherId"));
        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.findById(teacherId);
        subject.setTeacher(teacher);
        subjectService.updateTeacher(subject);
        request.setAttribute("subject", subject);
        request.setAttribute("status", "ok");
        request.setAttribute("message", "Subject with id " + subject.getId() + " assigned to class with id " + teacher.getId());
      }
      requestDispatcher = request.getRequestDispatcher("viewSubject.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find subject: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void assignTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify subject
    RequestDispatcher requestDispatcher;
    try {
      Long id = Long.valueOf(request.getParameter("id"));
      SubjectService subjectService = new SubjectService();
      Subject subject = subjectService.findById(id);
      TeacherService teacherService = new TeacherService();
      List<Teacher> teachers = teacherService.findAll();
      request.setAttribute("teachers", teachers);
      request.setAttribute("subject", subject);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "List of " + teachers.size() + " possible classes for subject with id " + subject.getId());
      requestDispatcher = request.getRequestDispatcher("assignTeacherToSubject.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find subject: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Add new subject
    String name = request.getParameter("name");
    String level = request.getParameter("level");
    Subject subject = new Subject();
    subject.setName(name);
    subject.setLevel(level);
    RequestDispatcher requestDispatcher;
    try {
      SubjectService subjectService = new SubjectService();
      subject = subjectService.add(subject);
      request.setAttribute("subject", subject);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "New subject added with id " + subject.getId());
      requestDispatcher = request.getRequestDispatcher("viewSubject.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot add subject: " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Modify subject
    Long id = Long.valueOf(request.getParameter("id"));
    String name = request.getParameter("name");
    String level = request.getParameter("level");
    Subject subject = new Subject();
    subject.setId(id);
    subject.setName(name);
    subject.setLevel(level);
    RequestDispatcher requestDispatcher;
    try {
      SubjectService subjectService = new SubjectService();
      subject = subjectService.update(subject);
      request.setAttribute("subject", subject);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "Subject with id " + subject.getId() + " updated");
      requestDispatcher = request.getRequestDispatcher("viewSubject.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot find subject by id " + id + ": " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }

  private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Delete subject
    Long id = Long.valueOf(request.getParameter("id"));
    RequestDispatcher requestDispatcher;
    try {
      SubjectService subjectService = new SubjectService();
      subjectService.delete(id);
      request.setAttribute("status", "ok");
      request.setAttribute("message", "Subject with id " + id + " deleted");
      request.setAttribute("subject", null);
      requestDispatcher = request.getRequestDispatcher("viewSubject.jsp");
    } catch (Exception ex) {
      request.setAttribute("status", "error");
      request.setAttribute("message", "Cannot delete subject with id " + id + ": " + ex);
      requestDispatcher = request.getRequestDispatcher("error.jsp");
    }
    response.setContentType("text/html");
    requestDispatcher.include(request, response);
  }
}
