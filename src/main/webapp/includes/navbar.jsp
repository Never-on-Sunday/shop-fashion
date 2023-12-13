<%@page import="model.bean.*"%>
<%@page import="java.util.*"%>
<%
ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
User authUser = (User) request.getSession().getAttribute("authUser");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

	<div class="container">
		<img src="./product-images/logoKC.png" class="rounded" alt="LogoKC"
			style="width: 50px;"> <a class="navbar-brand" href="index.jsp">Fashion
			shop KC</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="GetAllProductsServlet">Home</a></li>
				<%
				if (authUser != null && authUser.getrole().equals("client")) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="GetPersonalAccountServlet">Account</a></li>
				<%
				}
				%>
				<li class="nav-item"><a class="nav-link"
					href="GetCartProductsServlet">Cart <span
						class="badge badge-danger">${cart_list.size()}</span>
				</a></li>
				<%
				if (authUser != null) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="GetOrdersServlet">Orders</a></li>
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