<%@ page import="com.learners.academy.entity.Subject" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>View subject</title>
</head>
<body>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<h2>View subject</h2>
<%
    Subject subject = (Subject) request.getAttribute("subject");
    if (subject != null) {
%>
<h3>Subject with id <%=subject.getId()%>
</h3>
<strong>Name: </strong><span><%=subject.getName()%></span><br/>
<strong>Level: </strong><span><%=subject.getLevel()%></span>
<%
} else {
%>
<h3>Subject with id <%=request.getParameter("id")%> not found
</h3>
<%
    }
%>
</body>
</html>
