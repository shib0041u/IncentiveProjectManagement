<%@page import="com.org.DTO.Admin"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Profile Details</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/adminNav.jsp"%>
</head>
<body class= "bodybg">
<%Admin admin = (Admin)session.getAttribute("adminObject"); 
if(admin == null)
	response.sendRedirect("AdminLogin");
else{
	%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-white">
						<p class="fs-4 text-center">Personal Details </p>
						<%
						String p =(String) session.getAttribute("ok");
						session.removeAttribute("ok");
						if (p!=null)
						{
							out.println(p);
						}
							%>
						<form method="post">

							<input type="hidden" name="id" value="<%=admin.getId()%>">
							<div class="mb-3">
								<label class="form-label">Name</label> <input name="name"
									type="text" value="<%=admin.getName()%>" class="form-control"
									required>
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input name="qualification"
									type="text" value="<%=admin.getQualification() %>" class="form-control"
									required>
							</div>
							<div class="mb-3">
								<label class="form-label">Designation</label> <input name="designation"
									type="text" value="<%=admin.getDesignation() %>"
									class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Previous Organization</label> <input name="previous_organization"
									type="text" value="<%=admin.getPrevious_organization()%>"
									class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Previous salary</label> <input name="previous_salary"
									type="text" value="<%=admin.getPrevious_salary()%>"
									class="form-control" readonly="readonly" required>
							</div>
							
							<div class="mb-3">
								<label class="form-label">Current salary</label> <input name="current_salary"
									type="text" value="<%=admin.getCurrent_salary()%>"
									class="form-control" readonly="readonly" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Address</label> <input name="address"
									type="text" value="<%=admin.getAddress()%>"
									class="form-control" required>
							</div>
							
							<div class="mb-3">
								<label class="form-label">Email Address</label> <input
									name="email" value="<%=admin.getEmail()%>" type="email"
									class="form-control" readonly="readonly" required>
							</div>
							
							<button type="submit" class="btn bg-success text-white col-md-12" onclick="form.action='adminHomeChangePassword'">Change Password</button>
							<button type="submit" class="btn bg-secondary mt-1 text-white col-md-12" onclick="form.action='UpdateAdminById'">Update Profile</button>
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