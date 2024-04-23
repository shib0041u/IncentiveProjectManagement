<%@page import="com.org.DTO.Employee"%>
<%@page import="com.org.DTO.Admin"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home Page</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/empNav.jsp"%>
</head>
<body class= "bodybg">
<%Employee employee = (Employee)session.getAttribute("employeeObject"); 
if(employee == null)
	response.sendRedirect("EmployeeLogin");
else{
	%>
	<marquee direction="left" scrollamount="3"><h2 class="text-white">Employee home landing page</h2></marquee>
	<%
}
%>

</body>