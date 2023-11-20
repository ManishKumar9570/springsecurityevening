<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hi ${userName}</h1>
<h4>Roles Assigend : ${roles}</h4>
<p>Spring Security Rocks !! </p>

<sec:authorize access='hasAuthority("Trainer")'>
<a href="/springsecurityevening/trainer">Show Trainer's Dashboard</a>
</sec:authorize>
<sec:authorize access='hasAuthority("Coder")'>
<a href="/springsecurityevening/coder">Show Coder's Dashboard</a>
</sec:authorize>
<br>
<a href="/springsecurityevening/delete?username=${userName}">Delete your Account permanently</a>
&nbsp;
<a href="/springsecurityevening/changePassword?username=${userName}">Change Password</a>

<form:form action="logout" method="POST">
	<input type="submit" value="logout">
</form:form>

</body>
</html>