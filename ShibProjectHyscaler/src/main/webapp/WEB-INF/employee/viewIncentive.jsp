<%@page import="com.org.DTO.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Employee Incentive</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/empNav.jsp"%>
</head>
<body class= "bodybg">
<%Employee employee = (Employee)session.getAttribute("employeeObject"); 
if(employee == null)
	response.sendRedirect("EmployeeLogin");
else{
	%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-white">
						<p class="fs-4 text-center">View Incentives</p>
							<div class="mb-3">
								<label class="form-label">Incentive Percentage</label> <input name="incentive"
									type="text" value="<% if(employee.getIncentive()!=null)
															out.print(employee.getIncentive().getIncentivePercentage());
									else{
										out.print("NO Incentive acheived");
									}
														%>" class="form-control" required readonly="readonly">
							</div>

							<div class="mb-3">
								<label class="form-label">Bonus</label> <input name="bonus"
									type="text" value="<% if(employee.getIncentive()!=null)
															out.print(employee.getIncentive().getBonus());
									else{
										out.print("NO Incentive acheived");
									}
														%>" class="form-control" required readonly="readonly">
							</div>
							<div class="mb-3">
								<label class="form-label">Holiday Eligibility</label> <input name="eligiblity"
									type="text" value="<% if(employee.getIncentive()!=null)
															out.print(employee.getIncentive().getHolidayEligibility());
									else{
										out.print("NO Incentive acheived");
									}
														%>" class="form-control" required readonly="readonly">
							</div>
							<div class="mb-3">
								<label class="form-label">Holiday Nights</label> <input name="duration"
									type="text" value="<% if(employee.getHoliday()!=null)
															out.print(employee.getHoliday().getNight());
									else{
										out.print("N/A");
									}
														%>" class="form-control" required readonly="readonly">
							</div>
							<div class="mb-3">
								<label class="form-label">Location</label> <input name="location"
									type="text" value="<% if(employee.getHoliday()!=null)
															out.print(employee.getHoliday().getLocation());
									else{
										out.print("N/A");
									}
														%>" class="form-control" required readonly="readonly">
							</div>
							<div class="mb-3">
								<label class="form-label">Other Offers/Facilities</label> <input name="other"
									type="text" value="<% if(employee.getHoliday()!=null)
															out.print(employee.getHoliday().getOthers());
									else{
										out.print("N/A");
									}
														%>" class="form-control" required readonly="readonly">
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<%
}
%>

</body>