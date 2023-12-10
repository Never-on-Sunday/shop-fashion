<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
List<OrderDisplay> orders = (List<OrderDisplay>) request.getAttribute("allOrders");
request.setAttribute("currentPageServlet", "ManageOrdersServlet");
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Manage Orders</title>
<style>
.pickDateContanier {
	display: flex;
	margin: 0px 50px 0px 50px;
}
</style>
</head>
<body>
	<%@include file="/includes/navbarAdmin.jsp"%>

	<label style="display: flex; margin: 20px 0px 0px 80px;">Order
		time from to</label>
	<!-- Pick Date-->
	<form action="ManageOrdersServlet" method="get">
		<div class="pickDateContanier">
			<!-- Pick Date from-->
			<%@include file="/includes/pickDateFrom.jsp"%>
			<label style="display: flex; align-items: center;">to</label>
			<!-- Pick Date to-->
			<%@include file="/includes/pickDateTo.jsp"%>

			<div class="buttonConfirm">
				<button type="submit" class="btn btn-primary"
					style="margin: 10px 0px 0px 0px; color: white; background-color: #28a745;">Confirm</button>
			</div>
		</div>
	</form>


	<div class="container mt-5">
		<div class="d-flex justify-content-center row">
			<div class="col-md-10">
				<div class="rounded">
					<div class="table-responsive table-borderless">
						<table class="table">
							<thead>
								<tr>
									<!-- 									<th class="text-center">
										<div class="toggle-btn">
											<div class="inner-circle"></div>
										</div>
									</th> -->
									<th>Order #</th>
									<th>ProductID</th>
									<th>ProductName</th>
									<th>UserID</th>
									<th>UserName</th>
									<th>Quantity</th>
									<th>Created</th>
									<th>Status</th>
									<th>OrderPrice</th>
									<th></th>
								</tr>
							</thead>
							<tbody class="table-body">
								<%
								if (orders != null) {
									for (OrderDisplay orderDisplay : orders) {
								%>

								<tr class="cell-1">
									<td><%=orderDisplay.getOrder().getOrderId()%></td>
									<td><%=orderDisplay.getProduct().getId()%></td>
									<td><%=orderDisplay.getProduct().getName()%></td>
									<td><%=orderDisplay.getUser().getId()%></td>
									<td><%=orderDisplay.getUser().getusername()%></td>
									<td><%=orderDisplay.getOrder().getQuantity()%></td>
									<td><%=orderDisplay.getOrder().getDate()%></td>
									<td><span class="badge badge-success"><%=orderDisplay.getOrder().getStatus()%></span></td>
									<td><%=orderDisplay.getOrder().getOrderPrice()%></td>
									<td><i class="fa fa-ellipsis-h text-black-50"></i></td>
								</tr>
								<%
								}
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div style="display: flex; justify-content: flex-end;">
			<%@include file="/includes/pageNav.jsp"%>
		</div>
	</div>



	<%@ include file="/includes/footer.jsp"%>
</body>
</html>