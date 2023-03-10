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
<h2>View subject</h2>
<%
    Subject subject = (Subject) request.getAttribute("subject");
    if (subject != null) {
%>
<h3>Subject with id <%=subject.getId()%>
</h3>
<strong>Name: </strong><span><%=subject.getName()%></span><br/>
<strong>Level: </strong><span><%=subject.getLevel()%></span><br/>
<strong>Class: </strong><span><%=subject.getClazz() != null ? subject.getClazz().getLevel() + " " + subject.getClazz().getLine() : "(not assigned)"%></span><br/>
<strong>Teacher: </strong><span><%=subject.getTeacher() != null ? subject.getTeacher().getName() + " " + subject.getTeacher().getLastName() : "(not assigned)"%></span>
<form action="SubjectController" method="post">
    <input type="hidden" name="action" value="delete"/>
    <input type="hidden" name="id" value="<%=subject.getId()%>" />
    <input type="submit" value="Delete" />
</form>
<form action="modifySubject.jsp" method="post">
    <input type="hidden" name="action" value="modify"/>
    <input type="hidden" name="id" value="<%=subject.getId()%>" />
    <input type="hidden" name="name" value="<%=subject.getName()%>" />
    <input type="hidden" name="level" value="<%=subject.getLevel()%>" />
    <input type="submit" value="Modify" />
</form>
<form action="SubjectController" method="post">
    <input type="hidden" name="action" value="assignClazz"/>
    <input type="hidden" name="id" value="<%=subject.getId()%>"/>
    <input type="submit" value="Assign to class"/>
</form>
<form action="SubjectController" method="post">
    <input type="hidden" name="action" value="assignTeacher"/>
    <input type="hidden" name="id" value="<%=subject.getId()%>"/>
    <input type="submit" value="Assign to teacher"/>
</form>
<%
} else {
%>
<h3>Subject with id <%=request.getParameter("id")%> not found
</h3>
<%
    }
%>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
