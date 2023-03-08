<%@ page import="com.learners.academy.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>Student list</title>
</head>
<body>
<% if(request.getAttribute("message")!=null){%>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<%}%>
<h2>Student list</h2>
<ul>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        for (Student student : students) {
    %>
    <li>
        <h3>Student with id <%=student.getId()%></h3>
        <strong>Name: </strong><span><%=student.getName()%></span><br/>
        <strong>Lastname: </strong><span><%=student.getLastName()%></span><br/>
        <strong>eMail: </strong><span><%=student.getEmail()%></span><br/>
        <strong>Phone: </strong><span><%=student.getPhone()%></span><br/>
        <strong>Class: </strong><span><%=student.getClazz() != null ? student.getClazz().getLevel() + student.getClazz().getLine()  : "(not assigned)"%></span><br/>
        <form action="StudentController" method="get">
            <input type="hidden" name="id" value="<%=student.getId()%>" />
            <input type="submit" value="View" />
        </form>
    </li>
    <%
        }
    %>
</ul>
<form action="addStudent.jsp">
    <input type="submit" value="Add" />
</form>
Go to <a href="home.jsp">Home</a> page<br/>
</body>

</html>
