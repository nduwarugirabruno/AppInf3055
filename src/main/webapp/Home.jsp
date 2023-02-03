<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
        String L = request.getParameter("login");
        String P = request.getParameter("password");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
           
           <h1> Bienvenue <% out.println(L); %> </h1>
</body>
</html>