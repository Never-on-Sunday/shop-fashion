
<div class="modal fade <%=(showModal ? "show" : "")%> tabindex="
	-1" role="dialog" id="confirmDeleteProduct"
	aria-hidden="<%=(!showModal ? "true" : "")%>">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Confirm Delete</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>This product is already existed in an order, do you still
					want to delete this product?</p>
			</div>
			<form action="DeleteAProductServlet">
				<input type="hidden" name="confirmDelete" value="true" />
				<input type="hidden" name="product_id" value="<%=request.getParameter("product_id") %>" />
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Yes</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</form>

		</div>
	</div>
</div>
</html>