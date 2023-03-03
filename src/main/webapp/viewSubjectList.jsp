<%@ page import="com.learners.academy.entity.Subject" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>Subject list</title>
</head>
<body>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<h2>Subject list</h2>
<ul>
    <%
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
        for (Subject subject : subjects) {
    %>
    <li>
        <h3>Subject with id <%=subject.getId()%></h3>
        <strong>Name: </strong><span><%=subject.getName()%></span><br/>
        <strong>Level: </strong><span><%=subject.getLevel()%></span>

    </li>
    <%
        }
    %>
</ul>
</body>

</html>