<%@page import="model.bean.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("index.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/includes/head.jsp"%>
<title>Login</title>
<style>
#loginContainer {
	padding: 0px 300px;
}
</style>
</head>

<body>
	<%@include file="/includes/navbar.jsp"%>

	<div id="loginContainer">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="LoginServlet" method="post">
					<!-- Show success mess -->
					<%
					String status = (String) request.getAttribute("status");
					if (status != null) {
						request.removeAttribute("status");
					%>
					<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert" aria-label="close"
							title="close">x</a> <strong>Message! </strong>
						<%=status%>
					</div>
					<%
					}
					%>
					<div class="form-group">
						<label>User name</label> <input type="user" name="login-username"
							class="form-control" placeholder="User name">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							name="login-password" class="form-control" placeholder="Password">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Sign in</button>
					</div>

					<div class="text-center" style="margin: 20px 0px 0px 0px">
						<p>
							Not a member? <a href="register.jsp">Register</a>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>