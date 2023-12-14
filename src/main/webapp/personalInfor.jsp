<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.bean.*"%>
<%
AccountDisplay accountDisplay = (AccountDisplay) request.getAttribute("accountDisplay");
User user = (User) request.getSession().getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/includes/head.jsp"%>
<title>Insert title here</title>

<style>
body {
	margin-top: 20px;
	background-color: #f2f6fc;
	color: #69707a;
}

.img-account-profile {
	height: 10rem;
}

.rounded-circle {
	border-radius: 50% !important;
}

.card {
	box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50/ 15%);
}

.card .card-header {
	font-weight: 500;
}

.card-header:first-child {
	border-radius: 0.35rem 0.35rem 0 0;
}

.card-header {
	padding: 1rem 1.35rem;
	margin-bottom: 0;
	background-color: rgba(33, 40, 50, 0.03);
	border-bottom: 1px solid rgba(33, 40, 50, 0.125);
}

.form-control, .dataTable-input {
	display: block;
	width: 100%;
	padding: 0.875rem 1.125rem;
	font-size: 0.875rem;
	font-weight: 400;
	line-height: 1;
	color: #69707a;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #c5ccd6;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	border-radius: 0.35rem;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.nav-borders .nav-link.active {
	color: #0061f2;
	border-bottom-color: #0061f2;
}

.nav-borders .nav-link {
	color: #69707a;
	border-bottom-width: 0.125rem;
	border-bottom-style: solid;
	border-bottom-color: transparent;
	padding-top: 0.5rem;
	padding-bottom: 0.5rem;
	padding-left: 0;
	padding-right: 0;
	margin-left: 1rem;
	margin-right: 1rem;
}
</style>

</head>
<body>
	<%
	if (user.getrole().equals("client")) {
	%>
	<%@include file="/includes/navbar.jsp"%>
	<%
	} else if (user.getrole().equals("admin")) {
	%>
	<%@include file="/includes/navbarAdmin.jsp"%>
	<%
	} else {
	}
	%>
	<div class="container-xl px-4 mt-4">

		<hr class="mt-0 mb-4">
		<div class="row">

			<div class="col-xl-8">
				<!-- Account details card-->
				<div class="card mb-4">
					<div class="card-header">Account Details</div>
					<div class="card-body">
						<form action="UpdatePersonalAccountServlet" method="get">
							<!-- Form Group (username)-->
							<div class="mb-3">
								<label class="small mb-1" for="inputUsername">Your Full
									Name</label> <input class="form-control" id="inputUsername"
									name="fullName" type="text"
									placeholder="<%=accountDisplay.getPersonalInfor().getFullName()%>"
									value="<%=accountDisplay.getPersonalInfor().getFullName()%>">
							</div>
							<!-- Form Row-->
							<div class="row gx-3 mb-3">
								<!-- Form Group (first name)-->
								<div class="col-md-6">
									<label class="small mb-1" for="inputFirstName">User
										Name</label> <input class="form-control" id="inputFirstName"
										type="text" name="userName"
										placeholder="<%=accountDisplay.getUser().getusername()%>"
										value="<%=accountDisplay.getUser().getusername()%>" readonly>
								</div>
								<!-- Form Group (last name)-->
								<div class="col-md-6">
									<label class="small mb-1" for="inputLastName">Password</label>
									<input class="form-control" id="inputLastName" name="password"
										type="text"
										placeholder="<%=accountDisplay.getUser().getpassword()%>"
										value="<%=accountDisplay.getUser().getpassword()%>">
								</div>
							</div>
							<!-- Form Row        -->
							<div class="row gx-3 mb-3">
								<!-- Form Group (organization name)-->
								<div class="col-md-6">
									<label class="small mb-1" for="inputOrgName">Address</label> <input
										class="form-control" id="inputOrgName" type="text"
										name="address"
										placeholder="<%=accountDisplay.getPersonalInfor().getAddress()%>"
										value="<%=accountDisplay.getPersonalInfor().getAddress()%>">
								</div>
								<!-- Form Group (location)-->
								<div class="col-md-6">
									<label class="small mb-1" for="inputLocation">Phone
										Number</label> <input class="form-control" id="inputLocation"
										type="text" name="phoneNumber"
										placeholder="<%=accountDisplay.getPersonalInfor().getPhoneNumber()%>"
										value="<%=accountDisplay.getPersonalInfor().getPhoneNumber()%>">
								</div>
							</div>
							<!-- Save changes button-->
							<button class="btn btn-primary" type="submit">Save
								changes</button>
							<a class="btn btn-primary" href="GetPersonalAccountServlet"
								style="color: white;">Reset</a>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>