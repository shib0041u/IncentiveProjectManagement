<%@page import="com.org.DTO.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.org.DAO.EmployeeDao"%>
<%@page import="com.org.dao_Interfaces.EmployeeDaoInterface"%>
<%@page import="com.org.DTO.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View My Employees</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/adminNav.jsp"%>
</head>
<body class= "bodybg">
<%Admin admin = (Admin)session.getAttribute("adminObject"); 
if(admin == null)
	response.sendRedirect("AdminLogin");
else{
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Registered all employees</p>
<form method="post">
<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Full Name</th>
									<th>Email</th>
									<th>sell</th>
									<th>Address</th>
									<th>Unique ID</th>
								</tr>
							</thead>
							<tbody>
								<%
								EmployeeDaoInterface dao= new EmployeeDao() ;
								List<Employee> employee = dao.fetchAllRequests();
								if(employee!=null)
								for (Employee x : employee) {
								%>
								<tr>
								<td><%=x.getName() %></td>
								
								<td><%=x.getEmail() %></td>
								<td><%=x.getSell() %></td>
								<td><%=x.getAddress() %></td>
								<td><%=x.getId() %></td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table></div>
</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
}
%>

</body>