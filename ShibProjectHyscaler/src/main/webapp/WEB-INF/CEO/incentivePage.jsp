<%@page import="com.org.DTO.Incentive"%>
<%@page import="com.org.DAO.IncentiveDao"%>
<%@page import="com.org.dao_Interfaces.IncentiveInterface"%>
<%@page import="java.util.List"%>

<%@page import="com.org.DTO.Ceo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Incentive</title>
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
						<p class="fs-3 text-center">Incentives Details</p>
						<%String str =(String) session.getAttribute("message");
						if(str!=null){
							out.print(str);
						}
%>
<form method="post">
<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Sales Targets</th>
									<th>Incentive Percentage</th>
									<th>Bonus</th>
									<th>Holiday Package Eligibility</th>
									
								</tr>
							</thead>
							<tbody>
								<%
								IncentiveInterface incentive= new IncentiveDao() ;
								List<Incentive> list = incentive.fetchAll();
								if(list!=null)
								for (Incentive x : list) {
								%>
								<tr>
								<td><%=x.getSale() %></td>
								<td><%=x.getIncentivePercentage() %></td>
								<td><%=x.getBonus() %></td>
								<td><%=x.getHolidayEligibility()%></td>
								<%
								}
								%>
							</tbody>
						</table>
						
						</div>
						<input class="btn btn-sm btn-success" type= "submit" value="Add new incentive target" onclick="form.action='newTarget'"/>
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