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
<h2>Modify subject <%=request.getParameter("id")%>
</h2>
Please, insert subject data:<br/>
<form action="SubjectController" method="post">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
    <label>Name: <input type="text" name="name" value="<%=request.getParameter("name")%>"/></label><br/>
    <label>Level: <input type="text" name="level" value="<%=request.getParameter("level")%>"/></label><br/>
    <input type="hidden" name="action" value="modify"/>
    <input type="submit" value="Save"/>
    <input type="reset" value="Reset"/>
</form>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
