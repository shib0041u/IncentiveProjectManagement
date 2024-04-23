<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Register</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/indexNav.jsp"%>
</head>
<body class= "indexbg">
<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<p class="fs-4 text-center">Employee Registration</p>
						<%String str =(String) session.getAttribute("message"); 
						if(str!=null){
						out.println(str);
						session .removeAttribute("message");
						}%>
						<form action="EMPLOYEEregisterRequest" method="post" >
							<div class="mb-3">
								<label class="form-label">Name</label> <input name="name"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Qualification</label> <input name="qualification"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Current Designation</label> <input name="designation"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Current organization</label> <input name="current_organization"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Current monthly salary</label> <input name="previous_salary"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Expected monthly salary</label> <input name="expected_salary"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Address</label> <input name="address"
									type="text" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Email Address</label> <input
									name="email" type="email" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label> <input
									name="password" type="password" class="form-control" required>
							</div>

							<button type="submit" class="btn bg-success text-white col-md-12">Register</button>
						</form>

						<p class ="fs-4">
							Already have an account ?<a href="EmployeeLogin" class="text-decoration-none text-success">Login</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>