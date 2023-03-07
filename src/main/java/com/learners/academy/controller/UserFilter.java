package com.learners.academy.controller;

import com.learners.academy.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class UserFilter implements Filter {
  public void init(FilterConfig config) throws ServletException {
  }

  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    String path = ((HttpServletRequest) request).getRequestURI();
    if (path.contains("css") || path.contains("error.jsp") || path.contains("index.jsp") || path.contains("LoginController") || path.contains("login.jsp")) {
      chain.doFilter(request, response);
    } else {
      HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
      if (httpSession == null) {
        // No session so no user
        request.setAttribute("status", "warning");
        request.setAttribute("message", "No session");
        ((HttpServletResponse) response).sendRedirect("login.jsp");
      } else {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
          request.setAttribute("status", "warning");
          request.setAttribute("message", "No user");
          ((HttpServletResponse) response).sendRedirect("login.jsp");
        } else {
          chain.doFilter(request, response);
        }
      }
    }
  }
}
