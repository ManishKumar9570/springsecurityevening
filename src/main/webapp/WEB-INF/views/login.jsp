<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<!-- write some code to handle invalid login scenario -->
	<c:if test="${param.error !=null}">
		<i style="color:red">Invalid login or password</i>
	</c:if>
	<c:if test="${param.logout !=null}">
		<i style="color:red">You are successfully logout. Please sign in again</i>
	</c:if>
	<h1>My custom login form</h1>
	
	<form:form action="process-login" method="post">
	Username : <input type="text" name="username">
	<br/>
	Password : <input type="password" name="password">
	<br/>
	<input type="submit" value="Login"/>
	
	
	</form:form>
</body>
</html>