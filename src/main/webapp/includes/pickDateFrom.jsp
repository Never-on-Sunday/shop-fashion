<%
String dateFrom = (String) request.getAttribute("dateFrom");
if(dateFrom == null){
	dateFrom = "2000-01-01";
}
%>
<div class="md-form md-outline input-with-post-icon datepicker" style="margin: 10px;">
	<input placeholder="Select date" type="date" name="pickDateFrom" value="<%=dateFrom %>"
		class="form-control"><!--  <label for="example">Try me...</label> -->
</div>

