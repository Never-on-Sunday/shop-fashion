<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Insert title here</title>

<style>
.gradient-custom-3 {
	/* fallback for old browsers */
	background: #84fab0;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 0.5),
		rgba(143, 211, 244, 0.5));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(132, 250, 176, 0.5),
		rgba(143, 211, 244, 0.5))
}

.gradient-custom-4 {
	/* fallback for old browsers */
	background: #84fab0;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 1),
		rgba(143, 211, 244, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(132, 250, 176, 1),
		rgba(143, 211, 244, 1))
}

.form-control-lg {
	height: 40px;
	width: 400px;
}

.mb-4, .my-4 {
	margin-bottom: unset;
}
</style>
</head>
<body>

	<section class="vh-100 bg-image"
		style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
		<div class="mask d-flex align-items-center h-100 gradient-custom-3">
			<div class="container h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-9 col-lg-7 col-xl-6">
						<div class="card" style="border-radius: 15px;">
							<div class="card-body p-5">
								<h2 class="text-uppercase text-center mb-5">Create an
									account</h2>
								<!-- Show success mess -->
								<%
 								String status = (String) request.getAttribute("status");
								if (status != null) {
									request.removeAttribute("status");
								%>
								<div class="alert alert-success">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close" title="close">�</a> <strong>Message!
									</strong>
									<%=status%> 
								</div>
								<%
								}
								%>
								<form action="RegisterServlet" method="post">

									<div class="form-outline mb-4">
										<input type="text" id="form3Example1cg" name="userName"
											class="form-control form-control-lg" /> <label
											class="form-label" for="form3Example1cg">User name</label>
									</div>
									<div class="form-outline mb-4">
										<input type="password" id="form3Example4cg" name="password"
											class="form-control form-control-lg" /> <label
											class="form-label" for="form3Example4cg">Password</label>
									</div>

									<div class="form-outline mb-4">
										<input type="password" id="form3Example4cdg"
											class="form-control form-control-lg" /> <label
											class="form-label" for="form3Example4cdg">Repeat your
											password</label>
									</div>

									<div class="form-outline mb-4">
										<input type="text" id="form3Example1cg" name="fullName"
											class="form-control form-control-lg" /> <label
											class="form-label" for="form3Example1cg">Full name</label>
									</div>

									<div class="form-outline mb-4">
										<input type="text" id="form3Example1cg" name="address"
											class="form-control form-control-lg" /> <label
											class="form-label" for="form3Example1cg">Address</label>
									</div>

									<div class="form-outline mb-4">
										<input type="text" id="form3Example1cg" name="phoneNumber"
											class="form-control form-control-lg" /> <label
											class="form-label" for="form3Example1cg">Phone Number</label>
									</div>

									<div class="d-flex justify-content-center">
										<button type="submit"
											class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register</button>
									</div>

									<p class="text-center text-muted mt-5 mb-0">
										Have already an account? <a href="login.jsp"
											class="fw-bold text-body"><u>Login here</u></a>
									</p>

								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/includes/footer.jsp"%>
</body>
</html>