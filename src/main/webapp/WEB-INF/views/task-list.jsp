<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task List</title>
</head>
<body>

	<h2>Task :</h2>
	<hr>
	<c:forEach items="${taskPage.content}" var="item">
		<span>Title : </span>
		<span>${item.title}</span>
		<br>
		<span>Description : </span>
		<span>${item.description}</span>
		<br>
		<span>Completed : </span>
		<span>${item.completed}</span>
		<br>
		<a href="/task/delete/${item.id}">Delete </a><br>
		<a href="/task/update/${item.id}">Update </a><br>
		<a href="/task/toggle/${item.id}">Toggle</a>

		<hr>
	</c:forEach>

	<c:forEach begin="0" end="${taskPage.totalPages - 1}" var="i">

		<a href="${pageContext.request.contextPath}/task/list?page=${i}">
			${i + 1} </a>

	</c:forEach>
</body>
</html>