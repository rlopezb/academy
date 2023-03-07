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
<h2>Learner's Academy login</h2>
<div>
  Please, login with your user and password:<br/>
  <form action="LoginController" method="post">

    <label>Username</label>
    <input type="text" name="login"/><br/>

    <label>Password</label>
    <input type="password" name="password"/><br/>

    <input type="submit" value="Login"/>
    <input type="reset" value="Reset"/>
  </form>
</div>
</body>
</html>
