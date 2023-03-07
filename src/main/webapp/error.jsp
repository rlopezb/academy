<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>

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
<h2>Sorry, an error has occurred!</h2>
<div>
  HTTP error code is <%=response.getStatus() %><br/>
  Please go to <a href="home.jsp">Home</a> page
</div>
<% if(request.getAttribute("message")!=null){%>
<div><%=request.getAttribute("message")%></div>
<%}%>
</body>
</html>