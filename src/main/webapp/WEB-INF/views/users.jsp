<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/Style_home.css" />"
	rel="stylesheet">
<title>Users</title>
</head>
<body>

	<!--  Search -->
	<h2>Search by name or age:</h2>

	<div id="searchForm">
		<form method="POST" action="users/searchUser">

			<label class="labelsInput">Search</label> <input type='text'
				name='criteria' value='${criteria}' class="inputField"
				maxlength="25" />


			<div style="clear: both"></div>
			<input type="submit" value="Search" class="button">
			<div style="clear: both"></div>
		</form>
	</div>
	
	<!-- Add User -->
	<a href="<c:url value="/users/addUser"/>" id="newUserButton">New User</a>
	

	<!--  Table of Users -->

	<h2>List of users</h2>
	<table id="tableUsers">
		<tr id="tableHead">
			<th>ID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Admin</th>
			<th>Date</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>

		<c:forEach items="${listUsers}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.age}</td>
				<td>
					<c:choose>
						<c:when test="${user.admin eq true}">
							Yes				
						</c:when>
						<c:otherwise>
							No
						</c:otherwise>
					</c:choose></td>
				<td>${user.createdDate}</td>
				<td><a href="<c:url value="/users/editUser/${user.id}"/>">Edit</a></td>
				<td><a href="<c:url value="/users/removeUser/${user.id}"/>">Delete</a></td>
			</tr>
		</c:forEach>

		<!-- Paging -->

	</table>
	<table id="pageNumbers">
		<tr>
			<c:choose>
				<c:when test="${pageNum-1 < 1}">
					<td class="prevNextButton"><a
						href="<c:url value="/users?search=${criteria}&page=1"/>">Previous</a></td>

				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${pageNum-1 > lastPageNum}">
							<td class="prevNextButton"><a
								href="<c:url value="/users?search=${criteria}&page=${lastPageNum}"/>">Previous</a></td>
						</c:when>
						<c:otherwise>
							<td class="prevNextButton"><a
								href="<c:url value="/users?search=${criteria}&page=${pageNum-1}"/>">Previous</a></td>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>

			<c:forEach items="${listOfPages}" var="number">
				<td><a
					href="<c:url value="/users?search=${criteria}&page=${number}"/>">${number}</a></td>
			</c:forEach>

			<c:choose>
				<c:when test="${pageNum+1 < 1}">
					<td class="prevNextButton"><a
						href="<c:url value="/users?search=${criteria}&page=1"/>">Next</a></td>

				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${pageNum+1 > lastPageNum}">
							<td class="prevNextButton"><a
								href="<c:url value="/users?search=${criteria}&page=${lastPageNum}"/>">Next</a></td>
						</c:when>
						<c:otherwise>
							<td class="prevNextButton"><a
								href="<c:url value="/users?search=${criteria}&page=${pageNum+1}"/>">Next</a></td>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>

</body>

</html>
