<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style >
.error{
	color : red;
	font-size: 14px;
}
</style>
</head>
<body>
	<h1>Login Page</h1>
	
	<form:form action="login" method="POST"
		modelAttribute="loginDTO">
		<form:errors path="" cssClass="error"/>
		<div>
			<label for="email">Email:</label>
			<form:input path="email" />
			<form:errors path="email" cssClass="error" />
		</div>
		<br>
		<div>
			<label for="password">Password:</label>
			<form:password path="password" />
			<form:errors path="password" cssClass="error" />
		</div>
		<br>
		<button type="submit">Submit</button>
	</form:form>
	<a href="register">register</a>
</body>
</html>