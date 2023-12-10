<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Manage Revenue</title>
</head>
<body>
	<%@include file="/includes/navbarAdmin.jsp"%>

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
									<td><%=orderDisplay.getOrder().getDate() %></td>
									<td><span class="badge badge-success"><%=orderDisplay.getOrder().getStatus()%></span></td>
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