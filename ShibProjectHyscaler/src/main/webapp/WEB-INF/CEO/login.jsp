<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CEO Login</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/indexNav.jsp"%>
</head>
					
<body class= "indexbg">
<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<p class="fs-3 text-center">Login To CEO</p>
						<%
						String str =(String) session.getAttribute("CeoLogin");
						session.removeAttribute("CeoLogin");
						if(str!=null){
							out.print(str);
						}
						%>
						<form action="ceoLoginSubmit" method="post">
							<div class="mb-3">
								<label class="form-label">Email Address</label> <input
									name="email" type="email" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label> 
								<input name="password" type="password" class="form-control" required>
							</div>
							<button type="submit" class="btn bg-success text-white col-md-12">Login</button>
						</form>

						<p class ="fs-6">
							<a href="CEORegisterPage" class="text-decoration-none text-primary">New To Office CEO ? Register Here</a><br>
							<a href="CeoForgotPasswordPage" class="text-decoration-none text-danger">Forgot password ?</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>