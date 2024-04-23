<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Password</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/indexNav.jsp"%>
</head>
<body class= "indexbg">
<%String pass2=(String) session.getAttribute("pass2");
if(pass2==null){
	session.removeAttribute("pass2");
	response.sendRedirect("index");
}
else{%>
<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<form action="changePasswordFinalEmployee" method="post">
							<div class="mb-3">
							<p class="fs-3 ">Reset Password</p>
							<label class="form-label">Enter your New Password: </label> <input
									name="password" type="password" class="form-control" required>
							</div>
							<button type="submit" class="btn bg-primary text-white col-md-12">Change Password</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div><%} %>
</body>
</html>