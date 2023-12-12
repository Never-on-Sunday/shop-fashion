<style>
.modal-dialog {
	max-width: 600px;
}

.input-md {
	width: 250px;
}

.form-group {
	display: flex;
}

#product_image {
	margin-bottom: 15px;
}

#manageContainer {
	display: flex;
	justify-content: flex-end;
}
</style>
<!-- Modal -->
<div class="modal fade"
	id="editOrderButton<%=orderDisplay.getOrder().getOrderId()%>"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Edit Order</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<!-- Form Edit Order -->
			<div class="modal-body">
				<form class="form-horizontal" name="fAddProduct"
					action="UpdateAnOrderServlet" method="get">
					<fieldset>
						<!-- Form Name -->
						<legend><%=orderDisplay.getProduct().getName()%></legend>
						<!-- Image -->
						<div class="imageContainer" style="margin-bottom: 20px;">
							<img class="card-img-top"
								src="product-images/<%=orderDisplay.getProduct().getImage()%>"
								alt="Card image cap">
						</div>

						<!-- Select status -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="product_id">Status</label>
							<select name="selectStatus" class="form-select" aria-label="Default select example" style="margin-left: 16px;">
								<option value="<%=orderDisplay.getOrder().getStatus()%>" selected><%=orderDisplay.getOrder().getStatus()%></option>
								<%
								for (OrderStatus orderStatus : OrderStatus.values()) {
								%>
								<option value="<%=orderStatus.name()%>"><%=orderStatus.name()%></option>
								<%
								}
								%>
							</select>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="address">Address</label>
							<div class="col-md-4">
								<input id="address" name="address" value="<%=orderDisplay.getOrder().getAddress()%>"
									class="form-control input-md" required="" type="text">

							</div>
						</div>
						
						<input type="hidden" name="orderID" value="<%=orderDisplay.getOrder().getOrderId()%>"/>
						<!-- Submit Button -->
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save</button>
						</div>
				</form>

			</div>
		</div>
	</div>
</div>