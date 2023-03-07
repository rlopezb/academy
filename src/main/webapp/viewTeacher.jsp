<%@ page import="com.learners.academy.entity.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>View teacher</title>
</head>
<body>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<h2>View teacher</h2>
<%
    Teacher teacher = (Teacher) request.getAttribute("teacher");
    if (teacher != null) {
%>
<h3>Teacher with id <%=teacher.getId()%>
</h3>
<strong>Name: </strong><span><%=teacher.getName()%></span><br/>
<strong>Lastname: </strong><span><%=teacher.getLastName()%></span><br/>
<strong>eMail: </strong><span><%=teacher.getEmail()%></span><br/>
<strong>Phone: </strong><span><%=teacher.getPhone()%></span><br/>
<form action="TeacherController" method="post">
    <input type="hidden" name="id" value="<%=teacher.getId()%>" />
    <input type="hidden" name="action" value="delete"/>
    <input type="submit" value="Delete" />
</form>
<%
    } else {
%>
<h3>Teacher with id <%=request.getParameter("id")%> not found
</h3>
<%
    }
%>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
