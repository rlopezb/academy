<%@ page import="com.learners.academy.entity.Subject" %>
<%@ page import="java.util.List" %>
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
<h2>Subject list</h2>
<ul>
    <%
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
        for (Subject subject : subjects) {
    %>
    <li>
        <h3>Subject with id <%=subject.getId()%></h3>
        <strong>Name: </strong><span><%=subject.getName()%></span><br/>
        <strong>Level: </strong><span><%=subject.getLevel()%></span><br/>
        <strong>Class: </strong><span><%=subject.getClazz() != null ? subject.getClazz().getLevel() + " " + subject.getClazz().getLine() : "(not assigned)"%></span><br/>
        <strong>Teacher: </strong><span><%=subject.getTeacher() != null ? subject.getTeacher().getName() + " " + subject.getTeacher().getLastName() : "(not assigned)"%></span>
        <form action="SubjectController" method="get">
            <input type="hidden" name="id" value="<%=subject.getId()%>" />
            <input type="submit" value="Manage" />
        </form>
    </li>
    <%
        }
    %>
</ul>
<form action="addSubject.jsp">
    <input type="submit" value="Add" />
</form>
Go to <a href="home.jsp">Home</a> page<br/>
</body>

</html>
