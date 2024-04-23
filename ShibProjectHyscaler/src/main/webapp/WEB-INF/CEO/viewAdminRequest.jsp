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
<title>Requested Admin Applications</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/ceoNav.jsp"%>
</head>
<body class= "bodybg">
<%Ceo ceo = (Ceo)session.getAttribute("ceoObject"); 
if(ceo == null)
	response.sendRedirect("CEOLogin");
else{
	%>
	<div class="container p-3">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Requested Admins Details</p>
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
								AdminRequestDaoInterface dao= new AdminRequestDao() ;
								List<Admin_Request> admin = dao.fetchAllRequest();
								if(admin!=null)
								for (Admin_Request x : admin) {
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
								<input class ="btn btn-sm btn-danger" type= "submit" value="Block" onclick="form.action='blockAdminRequest'
												<%session.setAttribute("id",x.getId());%>">
								
								<%}
								else{
								%>
								<input class ="btn btn-sm btn-success" type= "submit" value="unblock" onclick="form.action='activeAdminRequest'
												<%session.setAttribute("id",x.getId());%>"><%}
								
								%>
								</td>
								
								<td><%=x.getAddress() %></td>
								<td><%=x.getEmail() %></td>
								<td><%=x.getId() %></td>
								<td><input class="btn btn-sm btn-success" type= "submit" value="Approve" onclick="form.action='registerAsAdmin'
												<%session.setAttribute("id",x.getId());%>"></td>
								<td><input class="btn btn-sm btn-danger" type= "submit" value="Reject" onclick="form.action='adminRequestRemove'
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