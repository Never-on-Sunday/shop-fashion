<%@page import="model.bean.User"%>
<%@page import="model.bean.Product"%>
<%@page import="model.bean.Cart"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
List<Product> products = (List<Product>) request.getAttribute("allProducts");
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Home</title>

<style>
.input-group {
	align-items: baseline;
	justify-content: space-between;
	display: flex;
}
</style>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">

		<div class="input-group">
			<form name="fSearch" action="SearchProductsServlet"
				style="display: flex;">
				<div class="form-outline" data-mdb-input-init>
					<input type="search" name="searchField" id="form1"
						class="form-control" placeholder="Search" />
				</div>
				<button type="submit" class="btn btn-primary" data-mdb-ripple-init>
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>

		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">
							Price: $<%=p.getPrice()%></h6>
						<h6 class="category">
							Category:
							<%=p.getCategory()%></h6>
						<h6 class="price">
							Description:
							<%=p.getDescription()%></h6>
						<h6 class="category">
							Status:
							<%=p.getStatus()%></h6>
						<%
						String readOnly = "nones";
						if (p.getStatus().equals("SoldOut")) {
							readOnly = "none";
						}
						%>
						<div class="mt-3 d-flex justify-content-between">
							<%@ include file="/includes/addToCartButton.jsp"%>
							<%@ include file="/includes/buyNowButton.jsp"%>

							<a class="btn btn-dark" data-toggle="modal"
								style="display: <%=readOnly%>; color: white;"
								data-target="#addToCartButton<%=p.getId()%>">Add to Cart</a> <a
								class="btn btn-primary" data-toggle="modal"
								data-target="#buyNowButton<%=p.getId()%>"
								style="display: <%=readOnly%>; color:white;">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>

		<div style="display: flex; justify-content: flex-end;">
			<%@include file="/includes/pageNavCustomer.jsp"%>
		</div>
	</div>

	<%@ include file="/includes/footer.jsp"%>
</body>
</html>