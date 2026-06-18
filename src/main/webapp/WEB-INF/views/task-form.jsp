<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task form</title>
</head>
<body>

<c:choose>
    <c:when test="${taskRequestDTO.id != null}">
    <h1>Update Task</h1>
        <form:form action="update" method="POST" modelAttribute="taskRequestDTO">
		<form:errors path="" cssClass="error" />
	
		<form:hidden path="id"/>
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
		<button type="submit">Update Task</button>
	</form:form>
    </c:when>
    <c:otherwise>
    
        <h1>Create Task</h1>
        <form:form action="add" method="POST" modelAttribute="taskRequestDTO">
		<form:errors path="" cssClass="error" />
	
		<form:hidden path="id"/>
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
    </c:otherwise>
</c:choose>
	

</body>
</html>