<%@page import="com.org.DTO.Ceo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Otp Approval</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/indexNav.jsp"%>
</head>
<body class= "indexbg">
<%Ceo ceo=(Ceo) session.getAttribute("CEO");
if(ceo==null){
	response.sendRedirect("index");
}
else{%>

<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<p class="fs-4 text-center">Register CEO</p>
						<form action="verifyOtpCeo" method="post" id="TimeOver">
							<div class="mb-3 ">
								<label class="form-label">OTP has sent to: shubhranshucontact@gmail.com </label>
								<label class="form-label" >Enter OTP: </label>
								<input name="OTP" type="text" class="form-control" required>
							</div>
							<button type="submit" class="btn bg-success text-white col-md-12">Verify</button>
							<div>Time left = <span id="timer"></span></div>
						</form>
						<form method="post" >
							<div class="d-grid gap-2" >
								<input type="hidden" class="btn bg-success text-white col-md-12" id="timeOUT"
										value="Resend Otp" onClick="form.action='CEOResendOtpRegistrationPage'">
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
</body>
</html>