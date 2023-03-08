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
        <li><a href="StudentController?action=view">View list of students</a></li>
        <li><a href="TeacherController?action=view">View list of teachers</a></li>
        <li><a href="SubjectController?action=view">View list of subjects</a></li>
        <li><a href="ClazzController?action=view">View list of classes</a></li>
    </ul>
</div>
</body>
</html>
