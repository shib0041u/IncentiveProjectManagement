<%@page import="com.org.DTO.Ceo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Holiday target</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/ceoNav.jsp"%>
</head>
<body class= "bodybg">
<%Ceo ceo = (Ceo)session.getAttribute("ceoObject"); 
if(ceo == null)
	response.sendRedirect("CEOLogin");
else{
	%>
<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<p class="fs-4 text-center">Add New Package</p>
						
						<form  method="post" >
							<div class="mb-3">
								<label class="form-label">Sales Targets</label> <input name="sale"
									type="number" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Night Spent</label> <input name="night"
									type="number" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Destination</label> <input name="location"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Other included offers</label> <input name="others"
									type="text" class="form-control" required>
							</div>
							
							<button type="submit" onclick="form.action='targetSaveHoliday'<%session.setAttribute("check", "p"); %>" class="btn bg-success text-white col-md-12">Save Target</button>
						</form>

						</div>
				</div>
			</div>
		</div>
	</div>
<%} %>
</body>
</html>