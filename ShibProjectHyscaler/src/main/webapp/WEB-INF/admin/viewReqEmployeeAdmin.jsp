<%@page import="com.org.DTO.Employee_Request"%>
<%@page import="java.util.List"%>
<%@page import="com.org.DAO.EmployeeRequestDao"%>
<%@page import="com.org.dao_Interfaces.EmployeeDaoRequestInterface"%>
<%@page import="com.org.DTO.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Requested Employee Applications</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/adminNav.jsp"%>
</head>
<body class= "bodybg">
<%Admin admin = (Admin)session.getAttribute("adminObject"); 
if(admin == null)
	response.sendRedirect("AdminLogin");
else{
	%>
	<div class="container ">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Requested Employee Details</p>
<form method="post">
						<table class="table">
							<thead>
								<tr>
									<th>Full Name</th>
									<th>Qualification</th>
									<th>Designation</th>
									<th>Current Organization</th>
									<th>Current Salary</th>
									<th>Expected Salary</th>
									<th>Change Status</th>
									<th>Address</th>
									<th>Email</th>
									<th>Unique ID</th>
									<th colspan="2" style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
								EmployeeDaoRequestInterface dao= new EmployeeRequestDao() ;
								List<Employee_Request> employee = dao.fetchAllRequests();
								if(employee!=null)
								for (Employee_Request x : employee) {
								%>
								<tr>
								<td><%=x.getName() %></td>
								<td><%=x.getQualification() %></td>
								<td><%=x.getDesignation() %></td>
								<td><%=x.getCurrent_organization() %></td>
								<td><%=x.getPrevious_salary() %></td>
								
								<td><input type="number" name="expected" value="<%=x.getExpected_salary()%>"/></td>
								<td>
								<% String status = x.getStatus();
								if(status.equals("Active"))
								{
								%>
								<input class ="btn btn-sm btn-danger" type= "submit" value="Block" onclick="form.action='blockEmployeeRequestAd'
												<%session.setAttribute("id",x.getId());%>">
								
								<%}
								else{
								%>
								<input class ="btn btn-sm btn-success" type= "submit" value="unblock" onclick="form.action='activeEmployeeRequestAd'
												<%session.setAttribute("id",x.getId());%>"><%}
								
								%>
								</td>
								
								<td><%=x.getAddress() %></td>
								<td><%=x.getEmail() %></td>
								<td><%=x.getId() %></td>
								<td><input class="btn btn-sm btn-success" type= "submit" value="Approve" onclick="form.action='registerAsEmployeeFromAd'
												<%session.setAttribute("id",x.getId());%>"></td>
								<td><input class="btn btn-sm btn-danger" type= "submit" value="Reject" onclick="form.action='employeesRequestRemovead'
												<%session.setAttribute("id",x.getId());%>"></td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
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
</html>