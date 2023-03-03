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
        <strong>Subject: </strong><span><%=clazz.getSubject()!=null?clazz.getSubject().getName()+" "+clazz.getSubject().getLevel():"(not assigned)"%></span><br/>
        <strong>Teacher: </strong><span><%=clazz.getTeacher()!=null?clazz.getTeacher().getName()+" "+clazz.getTeacher().getLastName():"(not assigned)"%></span><br/>
        <strong>Number: </strong><span><%=clazz.getNumber()%></span><br/>
        <strong>Room: </strong><span><%=clazz.getRoom()%></span><br/>
        <strong>Date: </strong><span><%=clazz.getDate()%></span>
    </li>
    <%
        }
    %>
</ul>
</body>

</html>
