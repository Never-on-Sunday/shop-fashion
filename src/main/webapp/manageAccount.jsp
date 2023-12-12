<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
List<AccountDisplay> allAccountDisplays = (List<AccountDisplay>) request.getAttribute("allAccountDisplays");
request.setAttribute("currentPageServlet", "ManageAccountsServlet");
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Insert title here</title>
</head>
<body>

	<%@include file="/includes/navbarAdmin.jsp"%>

	<%-- 	<label style="display: flex; margin: 20px 0px 0px 80px;">Order
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

			<div
				style="display: flex; flex-wrap: wrap; justify-content: center; align-items: center; width: -webkit-fill-available;">
				<h3>
					Total Income: $
					<%=total%></h3>
			</div>
		</div>
	</form> --%>


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
									<th>id #</th>
									<th>UserName</th>
									<th>Password</th>
									<th>Role</th>
									<th>FullName</th>
									<th>Address</th>
									<th>PhoneNumber</th>
									<th></th>
								</tr>
							</thead>
							<tbody class="table-body">
								<%
								if (allAccountDisplays != null) {
									for (AccountDisplay accountDisplay : allAccountDisplays) {
								%>

								<tr class="cell-1">
									<td><%=accountDisplay.getUser().getId()%></td>
									<td><%=accountDisplay.getUser().getusername()%></td>
									<td><%=accountDisplay.getUser().getpassword()%></td>
									<td><%=accountDisplay.getUser().getrole()%></td>
									<td><%=accountDisplay.getPersonalInfor().getFullName()%></td>
									<td><%=accountDisplay.getPersonalInfor().getAddress()%></td>
									<td><%=accountDisplay.getPersonalInfor().getPhoneNumber()%></td>
									<%-- 									<td><span
										class="badge badge-success color<%=orderDisplay.getOrder().getStatus()%>"><%=orderDisplay.getOrder().getStatus()%></span></td>
									<td><%=orderDisplay.getOrder().getOrderPrice()%></td> --%>
									<td><a type="button" class="btn btn-primary"
										href="GetPersonalAccountServlet?accID=<%=accountDisplay.getUser().getId()%>">Edit</a></td>
									<%-- <td><a type="button" class="btn btn-primary" style="background-color: red;"
										href="DeleteAnAccountServlet?accID=<%=accountDisplay.getUser().getId()%>">Delete</a></td> --%>

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

<%-- 		<div style="display: flex; justify-content: flex-end;">
			<%@include file="/includes/pageNav.jsp"%>
		</div> --%>
	</div>



	<%@ include file="/includes/footer.jsp"%>

</body>
</html>