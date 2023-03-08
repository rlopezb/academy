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
<h2>Learner's Academy</h2>
<div>
    Welcome to Learner's Academy:<br/>
    If you are already logged in, go to <a href="home.jsp">Home</a> page, please<br/>
    If not, go to <a href="login.jsp">Login</a> page<br/>
</div>
</body>
</html>
