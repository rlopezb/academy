<%@ page import="com.learners.academy.entity.Clazz" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>View clazz</title>
</head>
<body>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<h2>View clazz</h2>
<%
    Clazz clazz = (Clazz) request.getAttribute("clazz");
    if (clazz != null) {
%>
<h3>Clazz with id <%=clazz.getId()%>
</h3>
<strong>Level: </strong><span><%=clazz.getLevel()%></span><br/>
<strong>Line: </strong><span><%=clazz.getLine()%></span>
<%
} else {
%>
<h3>Clazz with id <%=request.getParameter("id")%> not found
</h3>
<%
}
%>
</body>
</html>
