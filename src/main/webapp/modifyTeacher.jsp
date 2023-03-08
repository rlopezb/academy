<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/academy.css">
    <title>Learner's Academy</title>
</head>
<body>
<% if (request.getAttribute("message") != null) {%>
<div class="toast ${status}">
    <%= request.getAttribute("message")%>
</div>
<%}%>
<h2>Modify teacher <%=request.getParameter("id")%>
</h2>
Please, insert teacher data:<br/>
<form action="TeacherController" method="post">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
    <label>Name: <input type="text" name="name" value="<%=request.getParameter("name")%>"/></label><br/>
    <label>Lastname: <input type="text" name="lastName" value="<%=request.getParameter("lastName")%>"/></label><br/>
    <label>eMail: <input type="text" name="email" value="<%=request.getParameter("email")%>"/></label><br/>
    <label>Phone: <input type="text" name="phone" value="<%=request.getParameter("phone")%>"/></label><br/>
    <input type="hidden" name="action" value="modify"/>
    <input type="submit" value="Save"/>
    <input type="reset" value="Reset"/>
</form>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
