<%@page import="com.org.DTO.Holiday"%>
<%@page import="java.util.List"%>
<%@page import="com.org.DAO.HolidayDAO"%>
<%@page import="com.org.dao_Interfaces.HolidayInterface"%>
<%@page import="com.org.DTO.Ceo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Holiday Package</title>
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
						<p class="fs-3 text-center">Holiday Package Details</p>
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
									<th>Night spent</th>
									<th>Location</th>
									<th>Other Offers</th>
									
								</tr>
							</thead>
							<tbody>
								<%
								HolidayInterface holiday= new HolidayDAO() ;
								List<Holiday> list = holiday.fetchAll();
								if(list!=null)
								for (Holiday x : list) {
								%>
								<tr>
								<td><%=x.getSale() %></td>
								<td><%=x.getNight() %></td>
								<td><%=x.getLocation() %></td>
								<td><%=x.getOthers()%></td>
								<%
								}
								%>
							</tbody>
						</table>
						
						</div>
						<input class="btn btn-sm btn-success" type= "submit" value="Add new Holiday package" onclick="form.action='newHolidayTarget'"/>
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