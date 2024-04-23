<%@page import="com.org.DTO.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
						<form  method="post">
							<div class="mb-3">
							<p class="fs-3 ">Sell Product</p>
									<label class="form-label">Product Amount</label> <input
									name="product" type="number" class="form-control" required>
							</div>
							<input class ="btn btn-sm btn-success" type= "submit" value="Sell Product" onclick="form.action='productSold'"/>
							<input class ="btn btn-sm btn-danger mt-1" type= "submit" value="Reset All My Sell Count" onclick="form.action='resetProduct'"/>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%
}
%>
</html>