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
import java.util.Date;
import java.util.List;

@WebServlet(name = "ClazzController", value = "/ClazzController")
public class ClazzController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get clazz(s)
        RequestDispatcher requestDispatcher;
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            try {
                ClazzService clazzService = new ClazzService();
                Clazz clazz = clazzService.findById(id);
                request.setAttribute("clazz", clazz);
                if(clazz==null){
                    request.setAttribute("status", "info");
                    request.setAttribute("message", "Clazz with id " + id + " not found");
                }else{
                    request.setAttribute("status", "ok");
                    request.setAttribute("message", "Clazz with id " + id + " found");
                }
                requestDispatcher = request.getRequestDispatcher("viewClazz.jsp");
            } catch (Exception ex) {
                request.setAttribute("status", "error");
                request.setAttribute("message", "Cannot find clazz by id " + id + ": " + ex);
                requestDispatcher = request.getRequestDispatcher("error.jsp");
            }
        } else {
            try {
                ClazzService clazzService = new ClazzService();
                List<Clazz> clazzes = clazzService.findAll();
                request.setAttribute("clazzes", clazzes);
                request.setAttribute("status", "ok");
                request.setAttribute("message", "List of " + clazzes.size() + " clazzes retrieved");
                requestDispatcher = request.getRequestDispatcher("viewClazzList.jsp");
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
        //Add new clazz
        Integer number = Integer.valueOf(request.getParameter("number"));
        Integer room = Integer.valueOf(request.getParameter("room"));
        Date date = new Date(request.getParameter("date"));
        Clazz clazz = new Clazz();
        clazz.setNumber(number);
        clazz.setRoom(room);
        clazz.setDate(date);
        RequestDispatcher requestDispatcher;
        try {
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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Modify clazz
        Long id = Long.valueOf(request.getParameter("id"));
        Integer number = Integer.valueOf(request.getParameter("number"));
        Integer room = Integer.valueOf(request.getParameter("room"));
        Date date = new Date(request.getParameter("date"));
        Clazz clazz = new Clazz();
        clazz.setId(id);
        clazz.setNumber(number);
        clazz.setRoom(room);
        clazz.setDate(date);
        RequestDispatcher requestDispatcher;
        try {
            ClazzService clazzService = new ClazzService();
            clazz = clazzService.update(clazz);
            request.setAttribute("clazz", clazz);
            request.setAttribute("status", "ok");
            request.setAttribute("message", "Clazz with id " + clazz.getId() + " updated");
            requestDispatcher = request.getRequestDispatcher("viewClazz.jsp");
        } catch (Exception ex) {
            request.setAttribute("status", "error");
            request.setAttribute("message", "Cannot find clazz by id " + id + ": " + ex);
            requestDispatcher = request.getRequestDispatcher("error.jsp");
        }
        response.setContentType("text/html");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Delete clazz
        Long id = Long.valueOf(request.getParameter("id"));
        RequestDispatcher requestDispatcher;
        try {
            ClazzService clazzService = new ClazzService();
            clazzService.delete(id);
            request.setAttribute("status", "ok");
            request.setAttribute("message", "Clazz with id " + id + " deleted");
            request.setAttribute("clazz", null);
            requestDispatcher = request.getRequestDispatcher("viewClazz.jsp");
        } catch (Exception ex) {
            request.setAttribute("status", "error");
            request.setAttribute("message", "Cannot delete clazz with id " + id + ": " + ex);
            requestDispatcher = request.getRequestDispatcher("error.jsp");
        }
        response.setContentType("text/html");
        requestDispatcher.include(request, response);
    }
}
