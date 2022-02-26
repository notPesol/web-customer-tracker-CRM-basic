<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!-- import SortUtil class for use with link for sort by .... -->
<%@ page import="com.pesol.springdemo.util.SortUtil"%>

<!-- construct a sort link for first name -->
<c:url var="sortLinkFirstName" value="/customer/list">
	<c:param name="sort"
		value="<%=Integer.toString(SortUtil.BY_FIRSTNAME)%>" />
</c:url>

<!-- construct a sort link for last name -->
<c:url var="sortLinkLastName" value="/customer/list">
	<c:param name="sort"
		value="<%=Integer.toString(SortUtil.BY_LASTNAME)%>" />
</c:url>

<!-- construct a sort link for email -->
<c:url var="sortLinkEmail" value="/customer/list">
	<c:param name="sort" value="<%=Integer.toString(SortUtil.BY_EMAIL)%>" />
</c:url>

<!DOCTYPE html>
<html>
<head>
<title>List Customers Page</title>

<!-- reference style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2><a id="header-link" href="list">CRM - Customer Relationship Manager</a></h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<!-- Add new button: Add customer -->
			<button class="add-button"
				onclick="window.location.href='showFormForAdd'; return false;">
				Add Customer</button>

			<!-- Add search form for search by first name or last name -->
			<div style="margin-bottom: 15px;">
				<form:form action="search" method="GET">
					<label>Search Customer:</label>
					<input autocomplete="off" type="text" name="theSearchName">
					<input type="submit" value="Search">
				</form:form>
			</div>

			<!-- add html table -->
			<table>
				<tr>
					<th><a href="${sortLinkFirstName}">First Name</a></th>
					<th><a href="${sortLinkLastName}">Last Name</a></th>
					<th><a href="${sortLinkEmail}">Email</a></th>
					<th>Action</th>
				</tr>

				<!-- loop over and print customers -->
				<c:forEach var="tempCustomer" items="${customers}">

					<!-- construct an update link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<!-- construct a delete link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>

						<td>
							<!-- display the update link --> <a href="${updateLink}">Update</a>
							| <!-- display the delete link --> <a href="${deleteLink}"
							onclick="return confirm('Are you sure...\nYou want to delete this customer?')">
								Delete </a> <!-- or 
								<a href="showFormForUpdate?customerId=${tempCustomer.id}">update</a>
							-->
						</td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>