<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>

<div align="center"> 
	<h1>Change Password</h1>
	
	<c:if test="${param.invalidPassword != null}">
	<i style="color:red;">Invalid Old Password</i>
	</c:if>
	
	<c:if test="${param.invalidConfirmPassword != null}">
	<i style="color:red;">New Password and Confirm password doesn't matched</i>
	</c:if>
	
<form:form action="save-password" method="post" modelAttribute="change_Password">
<label>Old Password</label>
<form:input path="oldPassword" /> 
<br>
<label>New Password</label>
<form:input path="newPassword" /> 
<br>
<label>confirm Password</label>
<form:input path="confirmPassword" /> 
<br>
<input type="submit" value="Change Password">
</form:form>


</div>

</body>
</html>