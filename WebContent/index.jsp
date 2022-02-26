<%-- 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> 
--%>

<!-- Redirect to customer/list -->

<%-- 
<%
	response.sendRedirect("customer/list");
%> 
--%>
<!-- or -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:redirect url="customer/list"/>