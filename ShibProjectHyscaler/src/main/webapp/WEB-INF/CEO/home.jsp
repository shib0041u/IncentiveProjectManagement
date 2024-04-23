<%@page import="com.org.DTO.Ceo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CEO Home Page</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/ceoNav.jsp"%>
</head>
<body class= "bodybg">
<%Ceo ceo = (Ceo)session.getAttribute("ceoObject"); 
if(ceo == null)
	response.sendRedirect("CEOLogin");
else{
	%>
	<marquee direction="left" scrollamount="3"><h2 class="text-white">CEO home landing page</h2></marquee>
	<%
}
%>

</body>
</html>