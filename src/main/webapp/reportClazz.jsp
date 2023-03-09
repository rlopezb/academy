<%@ page import="com.learners.academy.entity.Clazz" %>
<%@ page import="com.learners.academy.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learners.academy.entity.Subject" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>Learner's Academy</title>
</head>
<body>
<% if(request.getAttribute("message")!=null){%>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<%}%>
<h2>View clazz</h2>
<%
    Clazz clazz = (Clazz) request.getAttribute("clazz");
    if (clazz != null) {
%>
<h3>Clazz with id <%=clazz.getId()%>
</h3>
<strong>Level: </strong><span><%=clazz.getLevel()%></span><br/>
<strong>Line: </strong><span><%=clazz.getLine()%></span><br/>
<strong>Students: </strong><br/>
<ul>
    <%
        List<Student> students = clazz.getStudents();
        for (Student student : students) {
    %>
    <li>
        <strong>Name: </strong><span><%=student.getName()%></span><br/>
        <strong>Lastname: </strong><span><%=student.getLastName()%></span><br/>
        <strong>eMail: </strong><span><%=student.getEmail()%></span><br/>
        <strong>Phone: </strong><span><%=student.getPhone()%></span>
    </li>
    <%
        }
    %>
</ul>

<strong>Subjects: </strong><br/>
<ul>
    <%
        List<Subject> subjects = clazz.getSubjects();
        for (Subject subject : subjects) {
    %>
    <li>
        <strong>Name: </strong><span><%=subject.getName()%></span><br/>
        <strong>Level: </strong><span><%=subject.getLevel()%></span><br/>
        <strong>Class: </strong><span><%=subject.getClazz() != null ? subject.getClazz().getLevel() + " " + subject.getClazz().getLine() : "(not assigned)"%></span><br/>
        <strong>Teacher: </strong><span><%=subject.getTeacher() != null ? subject.getTeacher().getName() + " " + subject.getTeacher().getLastName() : "(not assigned)"%></span>
    </li>
    <%
        }
    %>
</ul>

<%
} else {
%>
<h3>Clazz with id <%=request.getParameter("id")%> not found
</h3>
<%
}
%>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
