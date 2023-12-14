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
<div class="modal fade" id="buyNowButton<%=p.getId()%>" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Message</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<!-- Form Add Product -->
			<div class="modal-body">
				<form class="form-horizontal" name="fAddToCart"
					action="OrderNowServlet?quantity=1&productId=<%=p.getId()%>" method="post">
					<fieldset>

						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="productMessage">Your
								note to shop</label>
							<div class="col-md-4">
								<input id="productMessage" name="productMessage" class="form-control input-md"
									required="" type="text">

							</div>
						</div>
					</fieldset>
					<!-- Submit Button -->
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Send</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>