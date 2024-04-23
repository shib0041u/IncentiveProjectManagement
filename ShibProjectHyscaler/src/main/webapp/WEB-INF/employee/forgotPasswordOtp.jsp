<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Approve Identity Employee</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/indexNav.jsp"%>
</head>
<body class= "indexbg">
<%String verify=(String) session.getAttribute("pass");
session.removeAttribute("pass");
if(verify==null){
	response.sendRedirect("index");
}
else{
verify = (String) session.getAttribute("email");
%>
<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<p class="fs-4 text-center">Verify OTP</p>
						<form action="verifyEmployeeOTP" method="post" id="TimeOver">
							<div class="mb-3 ">
								<label class="form-label">OTP has sent to: <%=verify %></label>
								<label class="form-label" >Enter OTP: </label>
								<input name="OTP" type="text" class="form-control" required>
							</div>
							<button type="submit" class="btn bg-success text-white col-md-12">Verify</button>
							<div>Time left = <span id="timer"></span></div>
						</form>
						<form >
							<div class="d-grid gap-2" >
								<input type="hidden" class="btn bg-success text-white col-md-12" id="timeOUT"
										value="Resend Otp" onClick="form.action='adminForgotPasswordPage'">
							 </div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
<%
} %>
<script type="text/javascript" src="<c:url value="/resources/components/Timer.js" /> "></script>
</html>