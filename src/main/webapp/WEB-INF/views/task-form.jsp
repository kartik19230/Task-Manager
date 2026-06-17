<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task form</title>
</head>
<body>

	<form:form action="add" method="POST" modelAttribute="taskRequestDTO">
		<form:errors path="" cssClass="error" />
		<div>
			<label for="title">Title :</label>
			<form:input path="title" />
			<form:errors path="title" cssClass="error" />
		</div>
		<br>
		<div>
			<label for="description">Description :</label>
			<form:input path="description" />
			<form:errors path="description" cssClass="error" />
		</div>
		<br>
		<button type="submit">Create Task</button>
	</form:form>
	
</body>
</html>