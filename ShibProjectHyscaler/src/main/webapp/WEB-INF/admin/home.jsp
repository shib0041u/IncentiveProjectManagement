
</html>
<%@page import="com.org.DTO.Admin"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home Page</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/adminNav.jsp"%>
</head>
<body class= "bodybg">
<%Admin admin = (Admin)session.getAttribute("adminObject"); 
if(admin == null)
	response.sendRedirect("AdminLogin");
else{
	%>
	<marquee direction="left" scrollamount="3"><h2 class="text-white">Admin home landing page</h2></marquee>
	<%
}
%>

</body>
</html>