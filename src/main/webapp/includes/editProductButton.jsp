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
<div class="modal fade" id="editProductButton<%=p.getId()%>"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Edit a product</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<!-- Form Add Product -->
			<div class="modal-body">
				<form class="form-horizontal" name="fAddProduct"
					action="EditProductServlet" method="post">
					<fieldset>
						<!-- Form Name -->
						<legend>PRODUCT</legend>
						<!-- Image -->
						<div class="imageContainer" style="margin-bottom: 20px;">
							<img class="card-img-top" src="<%=p.getImage()%>"
								alt="Card image cap">
						</div>

						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="product_id">Product
								ID</label>
							<div class="col-md-4">
								<input id="product_id" name="product_id" value="<%=p.getId()%>"
									class="form-control input-md" required="" type="text" readonly>

							</div>
						</div>
						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="product_name">Product
								name</label>
							<div class="col-md-4">
								<input id="product_name" name="product_name"
									value="<%=p.getName()%>" class="form-control input-md"
									required="" type="text">

							</div>
						</div>
						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="product_category">Product
								category</label>
							<div class="col-md-4">
								<input id="product_category" name="product_category"
									value="<%=p.getCategory()%>" class="form-control input-md"
									required="" type="text">

							</div>
						</div>
						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="product_price">Price</label>
							<div class="col-md-4">
								<input id="product_price" name="product_price"
									value="<%=p.getPrice()%>" class="form-control input-md"
									required="" type="text">

							</div>
						</div>
						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="description">Description</label>
							<div class="col-md-4">
								<input id="description" name="description"
									placeholder="<%=p.getDescription() %>" class="form-control input-md"
									required="" type="text">

							</div>
						</div>
						<!-- Select Input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="selectStatus">Status</label>
							<select name="selectStatus" class="form-select"
								aria-label="Default select example" style="margin-left: 16px;">
								<option value="<%=p.getStatus() %>" selected><%=p.getStatus() %></option>
								<%
								for (StatusProduct statusProduct : StatusProduct.values()) {
								%>
								<option value="<%=statusProduct.name()%>"><%=statusProduct.name()%></option>
								<%
								}
								%>
							</select>
						</div>
						<!-- File Button -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="filebutton">Image</label>
							<div class="col-md-4">
								<input id="product_image" name="product_image"
									value="<%=p.getImage()%>" class="form-control input-md"
									required="" type="text"> <input id="filebutton"
									name="filebutton" class="input-file" type="file">
							</div>
						</div>
					</fieldset>
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
