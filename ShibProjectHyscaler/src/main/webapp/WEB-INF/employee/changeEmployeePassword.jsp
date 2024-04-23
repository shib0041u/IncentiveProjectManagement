
<%@page import="com.org.DTO.Employee"%>
<%@page import="com.org.DTO.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Approve Identity CEO</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/empNav.jsp"%>
</head>
<body class= "bodybg">
<%Employee employee = (Employee)session.getAttribute("employeeObject"); 
if(employee == null)
	response.sendRedirect("EmployeeLogin");
else{
	%>
<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<p class="fs-4 text-center">Verify OTP</p>
						<form action="changeEmployeePasswordHome" method="post" id="TimeOver">
							<div class="mb-3 ">
								<label class="form-label">OTP has sent to: <%=employee.getEmail() %></label>
								<label class="form-label" >Enter OTP: </label>
								<input name="OTP" type="text" class="form-control" required>
								<label class="form-label" >Enter New Password: </label>
								<input name="password" type="password" class="form-control" required>
							</div>
							<button type="submit" class="btn bg-success text-white col-md-12">Verify</button>
							
						</form>
												
					</div>
				</div>
			</div>
		</div>
	</div>
<%
} %>

</html>