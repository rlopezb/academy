<%@ page import="com.learners.academy.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>View student</title>
</head>
<body>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<h2>View student</h2>
<%
    Student student = (Student) request.getAttribute("student");
    if (student != null) {
%>
<h3>Student with id <%=student.getId()%>
</h3>
<strong>Name: </strong><span><%=student.getName()%></span><br/>
<strong>Lastname: </strong><span><%=student.getLastName()%></span><br/>
<strong>eMail: </strong><span><%=student.getEmail()%></span><br/>
<strong>Phone: </strong><span><%=student.getPhone()%></span><br/>
<strong>Class: </strong><span><%=student.getClazz() != null ? student.getClazz().getLevel() + student.getClazz().getLine()  : "(not assigned)"%></span>
<form action="StudentController" method="post">
    <input type="hidden" name="id" value="<%=student.getId()%>" />
    <input type="hidden" name="action" value="delete"/>
    <input type="submit" value="Delete" />
</form>
<%
    } else {
%>
<h3>Student with id <%=request.getParameter("id")%> not found
</h3>
<%
    }
%>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
