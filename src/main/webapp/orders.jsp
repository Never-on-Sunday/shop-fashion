<%@page import="model.bean.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.dao.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

List<OrderDisplay> orders = (List<OrderDisplay>) request.getAttribute("allOrders");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/includes/head.jsp"%>
<title>Orders</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">OrderID</th>
					<th scope="col">ProductID</th>
					<th scope="col">ProductName</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Date</th>
					<th scope="col">Status</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>

				<%
				if (orders != null) {
					for (OrderDisplay orderDisplay : orders) {
				%>
				<tr>
					<td><%=orderDisplay.getOrder().getOrderId()%></td>
					<td><%=orderDisplay.getProduct().getId()%></td>
					<td><%=orderDisplay.getProduct().getName()%></td>
					<td><%=orderDisplay.getOrder().getQuantity()%></td>
					<td><%=orderDisplay.getOrder().getOrderPrice()%></td>
					<td><%=orderDisplay.getOrder().getDate()%></td>
					<td><span class="badge badge-success"><%=orderDisplay.getOrder().getStatus()%></span></td>
					<td><a class="btn btn-sm btn-danger"
						href="CancelOrderServlet?id=<%=orderDisplay.getOrder().getOrderId()%>">Cancel Order</a></td>
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