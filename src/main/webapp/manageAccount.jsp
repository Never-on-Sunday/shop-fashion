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


	<div class="container mt-5">
		<div class="d-flex justify-content-center row">
			<div class="col-md-10">
				<div class="rounded">
					<div class="table-responsive table-borderless">
						<table class="table">
							<thead>
								<tr>
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

									<td><a type="button" class="btn btn-primary"
										href="GetPersonalAccountServlet?accID=<%=accountDisplay.getUser().getId()%>">Edit</a></td>
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

	</div>



	<%@ include file="/includes/footer.jsp"%>

</body>
</html>