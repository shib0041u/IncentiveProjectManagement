<%@page import="com.org.DTO.Employee"%>
<%@page import="com.org.DAO.EmployeeDao"%>
<%@page import="com.org.dao_Interfaces.EmployeeDaoInterface"%>
<%@page import="com.org.DTO.Admin"%>
<%@page import="com.org.DAO.AdminDao"%>
<%@page import="com.org.DAO.AdminRequestDao"%>
<%@page import="com.org.dao_Interfaces.AdminRequestDaoInterface"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.org.dao_Interfaces.AdminDaoInterface"%>
<%@page import="com.org.DTO.Admin_Request"%>
<%@page import="java.util.List"%>
<%@page import="com.org.DTO.Ceo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registered Employees</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/ceoNav.jsp"%>
</head>
<body class= "bodybg">
<%Ceo ceo = (Ceo)session.getAttribute("ceoObject"); 
if(ceo == null)
	response.sendRedirect("CEOLogin");
else{
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Registered Employees Details</p>
<form method="post">
<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Full Name</th>
									<th>Qualification</th>
									<th>Previous Designation</th>
									<th>Previous Organization</th>
									<th>Previous Salary</th>
									<th>Current Salary</th>
									<th>Change Status</th>
									<th>Address</th>
									<th>Email</th>
									<th>Unique ID</th>
									<th>Joined By</th>
									<th>Product Sell</th>
									<th colspan="2" style="text-align: center;">Action</th>
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
								<td><%=x.getQualification() %></td>
								<td><%=x.getDesignation() %></td>
								<td><%=x.getPrevious_organization() %></td>
								<td><%=x.getPrevious_salary() %></td>
												
								<td><input type="number" name="expected" value="<%=x.getCurrent_salary()%>"/></td>
								<td>
								<% String status = x.getStatus();
								if(status.equals("Active"))
								{
								%>
								<input class ="btn btn-sm btn-danger" type= "submit" value="Block" onclick="form.action='blockAdmin'
												<%session.setAttribute("id",x.getId());%>">
								
								<%}
								else{
								%>
								<input class ="btn btn-sm btn-success" type= "submit" value="Approve" onclick="form.action='activeAdmin'
												<%session.setAttribute("id",x.getId());%>"><%}
								
								%>
								</td>
								
								<td><%=x.getAddress() %></td>
								<td><%=x.getEmail() %></td>
								<td><%=x.getId() %></td>
								<td><%=x.getJoinedBy() %></td>
								<td><%=x.getSell() %></td>
								<td><input type= "submit" value="Update Salary" onclick="form.action='updateSalaryAdmin'
												<%session.setAttribute("id",x.getId());%>"></td>
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
</html>