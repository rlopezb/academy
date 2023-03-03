package com.learners.academy.controller;

import com.learners.academy.entity.Teacher;
import com.learners.academy.service.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeacherController", value = "/TeacherController")
public class TeacherController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get teacher(s)
        RequestDispatcher requestDispatcher;
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            try {
                TeacherService teacherService = new TeacherService();
                Teacher teacher = teacherService.findById(id);
                request.setAttribute("teacher", teacher);
                if(teacher==null){
                    request.setAttribute("status", "info");
                    request.setAttribute("message", "Teacher with id " + id + " not found");
                }else{
                    request.setAttribute("status", "ok");
                    request.setAttribute("message", "Teacher with id " + id + " found");
                }
                requestDispatcher = request.getRequestDispatcher("viewTeacher.jsp");
            } catch (Exception ex) {
                request.setAttribute("status", "error");
                request.setAttribute("message", "Cannot find teacher by id " + id + ": " + ex);
                requestDispatcher = request.getRequestDispatcher("error.jsp");
            }
        } else {
            try {
                TeacherService teacherService = new TeacherService();
                List<Teacher> teachers = teacherService.findAll();
                request.setAttribute("teachers", teachers);
                request.setAttribute("status", "ok");
                request.setAttribute("message", "List of " + teachers.size() + " teachers retrieved");
                requestDispatcher = request.getRequestDispatcher("viewTeacherList.jsp");
            } catch (Exception ex) {
                request.setAttribute("status", "error");
                request.setAttribute("error", "Cannot list all teachers: " + ex);
                requestDispatcher = request.getRequestDispatcher("error.jsp");
            }
        }
        response.setContentType("text/html");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Add new teacher
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setLastName(lastName);
        teacher.setEmail(email);
        teacher.setPhone(phone);
        RequestDispatcher requestDispatcher;
        try {
            TeacherService teacherService = new TeacherService();
            teacher = teacherService.add(teacher);
            request.setAttribute("teacher", teacher);
            request.setAttribute("status", "ok");
            request.setAttribute("message", "New teacher added with id " + teacher.getId());
            requestDispatcher = request.getRequestDispatcher("viewTeacher.jsp");
        } catch (Exception ex) {
            request.setAttribute("status", "error");
            request.setAttribute("message", "Cannot add teacher: " + ex);
            requestDispatcher = request.getRequestDispatcher("error.jsp");
        }
        response.setContentType("text/html");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Modify teacher
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setLastName(lastName);
        teacher.setEmail(email);
        teacher.setPhone(phone);
        RequestDispatcher requestDispatcher;
        try {
            TeacherService teacherService = new TeacherService();
            teacher = teacherService.update(teacher);
            request.setAttribute("teacher", teacher);
            request.setAttribute("status", "ok");
            request.setAttribute("message", "Teacher with id " + teacher.getId() + " updated");
            requestDispatcher = request.getRequestDispatcher("viewTeacher.jsp");
        } catch (Exception ex) {
            request.setAttribute("status", "error");
            request.setAttribute("message", "Cannot find teacher by id " + id + ": " + ex);
            requestDispatcher = request.getRequestDispatcher("error.jsp");
        }
        response.setContentType("text/html");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Delete teacher
        Long id = Long.valueOf(request.getParameter("id"));
        RequestDispatcher requestDispatcher;
        try {
            TeacherService teacherService = new TeacherService();
            teacherService.delete(id);
            request.setAttribute("status", "ok");
            request.setAttribute("message", "Teacher with id " + id + " deleted");
            request.setAttribute("teacher", null);
            requestDispatcher = request.getRequestDispatcher("viewTeacher.jsp");
        } catch (Exception ex) {
            request.setAttribute("status", "error");
            request.setAttribute("message", "Cannot delete teacher with id " + id + ": " + ex);
            requestDispatcher = request.getRequestDispatcher("error.jsp");
        }
        response.setContentType("text/html");
        requestDispatcher.include(request, response);
    }
}
