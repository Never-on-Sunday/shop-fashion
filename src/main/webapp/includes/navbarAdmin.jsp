<%@page import="model.bean.*"%>
<%@page import="java.util.*"%>
<%
ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
User authUser = (User) request.getSession().getAttribute("authUser");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">E-Commerce Cart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="ManageProductsServlet">Home</a></li>
				<%
				if (authUser != null) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="ManageOrdersServlet">Manage Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>

	<div>
		<label> <%
 if (authUser != null) {
 %> Hello, <%=authUser.getusername()%> <%
 }
 %>


		</label>
	</div>
</nav>