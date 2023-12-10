<%@page import="model.bean.*"%>
<%@page import="java.util.*"%>
<%@page import="model.dao.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("person", auth);
}

List<Cart> cartProduct = (List<Cart>) request.getAttribute("cartProduct");
double total = (double) request.getAttribute("total");

%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>E-Commerce Cart</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container my-3">
		<div class="d-flex py-3">
			<h3>Total Price: $ ${(total>0)?dcf.format(total):0}</h3>
			<a class="mx-3 btn btn-primary" href="CheckOutServlet">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
						//System.out.print("c size " + c.getPrice());
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=dcf.format(c.getPrice())%></td>
					<td>
						<form action="OrderNowServlet" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre"
									href="QuantityIncDecServlet?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a> <input type="text"
									name="quantity" class="form-control"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-decre"
									href="QuantityIncDecServlet?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="RemoveFromCartServlet?id=<%=c.getId()%>"
						class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>

	<%@include file="/includes/footer.jsp"%>

</body>
</html>