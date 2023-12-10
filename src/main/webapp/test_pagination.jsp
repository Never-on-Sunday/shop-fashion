<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<nav aria-label="...">
		<ul class="pagination">
			<!-- 			<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li> -->
			<li class="page-item"><a class="page-link" href="ManageProductsServlet?page=previous">Previous</a></li>
			<li class="page-item disable"><a class="page-link" href="#"
				name="page_current">1</a></li>
			<!-- 			<li class="page-item"><a class="page-link" href="#">4</a></li>
			<li class="page-item active"><a class="page-link" href="#">3</a></li> -->
			<li class="page-item"><a class="page-link"
				href="ManageProductsServlet?page=next" name="page_next">Next</a></li>
		</ul>
	</nav>


	<%@ include file="/includes/footer.jsp"%>
</body>
</html>