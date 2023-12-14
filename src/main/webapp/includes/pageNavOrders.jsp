	<nav aria-label="...">
		<ul class="pagination">
			<!-- 			<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li> -->
			<li class="page-item"><a class="page-link" href="GetOrdersServlet?page=prev">Previous</a></li>
			<li class="page-item disable"><a class="page-link" href="GetOrdersServlet?page=current"
				name="page_current"><%=request.getSession().getAttribute("idxPage") %></a></li>
			<!-- 			<li class="page-item"><a class="page-link" href="#">4</a></li>
			<li class="page-item active"><a class="page-link" href="#">3</a></li> -->
			<li class="page-item"><a class="page-link"
				href="GetOrdersServlet?page=next" name="page_next">Next</a></li>
		</ul>
	</nav>