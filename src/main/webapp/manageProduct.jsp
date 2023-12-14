<%@page import="model.bean.User"%>
<%@page import="model.bean.Product"%>
<%@page import="model.bean.Cart"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<%@include file="/includes/navbarAdmin.jsp"%>

	<div class="container">
		<!-- Search -->
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

		<!-- Show all products -->
		<div class="card-header my-3">All Products</div>

		<!-- Show success mess -->
		<%
		String status = (String) request.getAttribute("status");
		if (status != null) {
			request.removeAttribute("status");
			if (status.equals("success")) {
		%>
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close"
				title="close">x</a> <strong>Success! </strong>
			<%=status%>
		</div>
		<%
		} else if (status.equals("warningDelete")) {

		boolean showModal = true;
		%>

		<%@include file="/includes/warningDeleteMessage.jsp"%>
		<%
		} else {

		}
		}
		%>

		<%@include file="/includes/addProductButton.jsp"%>

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
						<div class="mt-3 d-flex justify-content-between">
							<%@ include file="/includes/editProductButton.jsp"%>
							<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#editProductButton<%=p.getId()%>">Edit</button>
							<%@ include file="/includes/deleteProductButton.jsp"%>
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
			<%@include file="/includes/pageNav.jsp"%>
		</div>
	</div>

	<script type="text/javascript">
		$(window).on('load', function() {
			$('#confirmDeleteProduct').modal('show');
		});
	</script>
	<%@ include file="/includes/footer.jsp"%>
</body>
</html>