<%@ page import="com.learners.academy.entity.Teacher" %>
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
<h2>Teacher list</h2>
<ul>
    <%
        List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");
        for (Teacher teacher : teachers) {
    %>
    <li>
        <h3>Teacher with id <%=teacher.getId()%></h3>
        <strong>Name: </strong><span><%=teacher.getName()%></span><br/>
        <strong>Lastname: </strong><span><%=teacher.getLastName()%></span><br/>
        <strong>eMail: </strong><span><%=teacher.getEmail()%></span><br/>
        <strong>Phone: </strong><span><%=teacher.getPhone()%></span>
        <form action="TeacherController" method="get">
            <input type="hidden" name="id" value="<%=teacher.getId()%>" />
            <input type="submit" value="View" />
        </form>
    </li>
    <%
        }
    %>
</ul>
<form action="addTeacher.jsp">
    <input type="submit" value="Add" />
</form>
Go to <a href="home.jsp">Home</a> page<br/>
</body>

</html>
