<nav class="navbar navbar-expand-lg navbar-dark ">
	<div class="container-fluid">
		<a class="navbar-brand" href="AdminHomePage"><i
			class="fa-solid fa-school"></i>Xyz Pvt. Ltd.</a>
    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item mx-1">
				<div  class="btn-group">
				  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
				    View Office Database
				  </button>
				  <ul  class="dropdown-menu dropdown-menu-lg-end">
				    <li><a class="dropdown-item" href="viewAllAdminAdmin">View All Admins</a></li>
				    <li><a class="dropdown-item" href="viewAllEmployeesAdmin">View All Employees</a></li>
				  </ul>
				  </div>
									
				<li class="nav-item">
				<form class="nav-link active" aria-current="page" method="post" action="viewReqEmployeeAdmin">
				<i class="fa-solid fa-user"></i><input type= "submit" value= "View Employee Request">
				</form></li>
				<li class="nav-item">
				<form class="nav-link active" aria-current="page" method="post" action="viewMeAdmin">
				<i class="fa-solid fa-user"></i><input type= "submit" value= "View Personal Data">
				</form></li>
				<li class="nav-item">
				<form class="nav-link active" aria-current="page" method="post" action="adminLogOut">
				<i class="fa-solid fa-user"></i><input type= "submit" value= "Logout">
				</form></li>
		</ul>
		
		</div>
			
			
  </div>
</nav>	