<%@ page import="com.learners.academy.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student list</title>
</head>
<body>
<h2>Student list</h2>
<ul>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        for (Student student : students) {
    %>
    <li>
        <h3>Student with id <%=student.getId()%></h3>
        <strong>Name: </strong><span><%=student.getName()%></span></br>
        <strong>Lastname: </strong><span><%=student.getLastName()%></span></br>
        <strong>eMail: </strong><span><%=student.getEmail()%></span></br>
        <strong>Phone: </strong><span><%=student.getPhone()%></span></br>
        <strong>Class: </strong><span><%=student.getClazz()!=null?student.getClazz().getNumber():"(not assigned)"%></span></br>

    </li>
    <%
        }
    %>
</ul>
</body>
</html>
