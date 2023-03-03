package com.learners.academy.controller;

import com.learners.academy.entity.Subject;
import com.learners.academy.service.SubjectService;

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
                if(subject==null){
                    request.setAttribute("status", "info");
                    request.setAttribute("message", "Subject with id " + id + " not found");
                }else{
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
                requestDispatcher = request.getRequestDispatcher("viewSubjectList.jsp");
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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Modify subject
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
