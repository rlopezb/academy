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
    <ul>
        <li><a href="StudentController">View list of students</a></li>
        <li><a href="TeacherController">View list of teachers</a></li>
        <li><a href="SubjectController">View list of subjects</a></li>
        <li><a href="ClazzController">View list of classes</a></li>
    </ul>
</div>
</body>
</html>
