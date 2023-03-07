<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <link rel="stylesheet" href="css/academy.css">
  <title>Learner's Academy</title>
</head>
<body>
<div class="toast ${status}">
  <%= request.getAttribute("message")%>
</div>
<h2>Add student</h2>
Please, insert student data:<br/>
<form action="StudentController" method="post">
  <label>Name: <input type="text" name="name"/></label><br/>
  <label>Lastname: <input type="text" name="lastName"/></label><br/>
  <label>eMail: <input type="text" name="email"/></label><br/>
  <label>Phone: <input type="text" name="phone"/></label><br/>
  <input type="hidden" name="action" value="add"/>
  <input type="submit" value="Add"/>
  <input type="reset" value="Reset"/>
</form>
</body>
</html>
