<%@ page import="com.learners.academy.entity.Clazz" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>Clazz list</title>
</head>
<body>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<h2>Clazz list</h2>
<ul>
    <%
        List<Clazz> clazzes = (List<Clazz>) request.getAttribute("clazzes");
        for (Clazz clazz : clazzes) {
    %>
    <li>
        <h3>Clazz with id <%=clazz.getId()%></h3>
        <strong>Level: </strong><span><%=clazz.getLevel()%></span><br/>
        <strong>Line: </strong><span><%=clazz.getLine()%></span>
    </li>
    <%
        }
    %>
</ul>
</body>

</html>