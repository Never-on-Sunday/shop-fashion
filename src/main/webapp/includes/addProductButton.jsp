<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>

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
</head>
<body>
	<!-- Button trigger modal -->
	<div id="manageContainer">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#addProductButton" style="background-color: #28a745;">Add
			Product</button>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="addProductButton" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add a new
						Product</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<!-- Form Add Product -->
				<div class="modal-body">
					<form class="form-horizontal" name="fAddProduct"
						action="AddAProduct" method="post">
						<fieldset>
							<!-- Form Name -->
							<legend>PRODUCT</legend>
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="product_id">Product
									ID</label>
								<div class="col-md-4">
									<input id="product_id" name="product_id"
										value="<%=request.getAttribute("nextProductID")%>"
										class="form-control input-md" required="" type="text" readonly>

								</div>
							</div>
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="product_name">Product
									name</label>
								<div class="col-md-4">
									<input id="product_name" name="product_name"
										placeholder="Product name" class="form-control input-md"
										required="" type="text">

								</div>
							</div>
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="product_category">Product
									category</label>
								<div class="col-md-4">
									<input id="product_category" name="product_category"
										placeholder="Product category" class="form-control input-md"
										required="" type="text">

								</div>
							</div>
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="product_price">Price</label>
								<div class="col-md-4">
									<input id="product_price" name="product_price"
										placeholder="Price" class="form-control input-md" required=""
										type="text">

								</div>
							</div>
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="description">Description</label>
								<div class="col-md-4">
									<input id="description" name="description"
										placeholder="Description" class="form-control input-md"
										required="" type="text">

								</div>
							</div>
							<!-- Select Input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="selectStatus">Status</label>
								<select name="selectStatus" class="form-select"
									aria-label="Default select example" style="margin-left: 16px;">
									<option value="InStock" selected>InStock</option>
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
										placeholder="Image path" class="form-control input-md"
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

	<%@ include file="/includes/footer.jsp"%>
</body>
</html>