<%@ page import="com.learners.academy.entity.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learners.academy.entity.Subject" %>
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
<h2>Assign subject <%=request.getParameter("id")%> to class</h2>
Please, select a class:<br/>
<form action="SubjectController" method="post">
    <input type="hidden" name="subjectId" value="<%=request.getParameter("id")%>"/>
    <label>Class: <select name="teacherId">
        <option value=""></option>
        <%
            List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");
            Subject subject = (Subject) request.getAttribute("subject");
            for (Teacher teacher : teachers) {
                if (subject.getTeacher() != null && subject.getTeacher().getId() == teacher.getId()) {%>
        <option selected value="<%=teacher.getId()%>"><%=teacher.getName() + " " + teacher.getLastName()%>
        </option>
        <% } else { %>
        <option value="<%=teacher.getId()%>"><%=teacher.getName() + " " + teacher.getLastName()%>
        </option>
        <%
                }
            }
        %>
    </select>
    </label><br/>
    <input type="hidden" name="action" value="modifyTeacher"/>
    <input type="submit" value="Assign"/>
    <input type="reset" value="Reset"/>
</form>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
