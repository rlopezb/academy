<%@ page import="com.learners.academy.entity.Clazz" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learners.academy.entity.Student" %>
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
<h2>Assign student <%=request.getParameter("id")%> to class</h2>
Please, select a class:<br/>
<form action="StudentController" method="post">
    <input type="hidden" name="studentId" value="<%=request.getParameter("id")%>"/>
    <label>Class: <select name="clazzId">
        <option value=""></option>
        <%
            List<Clazz> clazzes = (List<Clazz>) request.getAttribute("clazzes");
            Student student = (Student) request.getAttribute("student");
            for (Clazz clazz : clazzes) {
                if (student.getClazz() != null && student.getClazz().getId() == clazz.getId()) {%>
        <option selected value="<%=clazz.getId()%>"><%=clazz.getLevel() + " " + clazz.getLine()%>
        </option>
        <% } else { %>
        <option value="<%=clazz.getId()%>"><%=clazz.getLevel() + " " + clazz.getLine()%>
        </option>
        <%
                }
            }
        %>
    </select>
    </label><br/>
    <input type="hidden" name="action" value="modifyClazz"/>
    <input type="submit" value="Assign"/>
    <input type="reset" value="Reset"/>
</form>
Go to <a href="home.jsp">Home</a> page<br/>
</body>
</html>
