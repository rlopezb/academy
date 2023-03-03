<%@ page import="com.learners.academy.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>Student list</title>
</head>
<body>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
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
        <strong>Class: </strong><span><%=student.getClazz() != null ? student.getClazz().getNumber() : "(not assigned)"%></span>

    </li>
    <%
        }
    %>
</ul>
</body>

</html>
