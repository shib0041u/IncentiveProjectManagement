<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CEO Change Password</title>
<%@ include file="../resources/components/globalCSS.jsp"%>
<%@ include file="../resources/components/indexNav.jsp"%>
</head>
<body class= "indexbg">
<div class="container p-5 " >
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="car paint-card">
					<div class="card-body bg-light">
						<form action="fectchByEmailCEO" method="post">
							<div class="mb-3">
							<p class="fs-3 ">Forgot Password</p>
							<p><%String str= (String) session.getAttribute("forgetFailed");
							if(str!=null)
							out.println(str);
							session.removeAttribute("forgetFailed");%></p>
								<label class="form-label">Enter your mail id: </label> <input
									name="email" type="email" class="form-control" required>
							</div>
							<button type="submit" class="btn bg-primary text-white col-md-12">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>