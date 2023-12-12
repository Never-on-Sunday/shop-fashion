<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%

String dateTo = (String) request.getAttribute("dateTo");
if(dateTo == null){
	LocalDate currentDate = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	dateTo = currentDate.format(formatter);
}
%>
<div class="md-form md-outline input-with-post-icon datepicker" style="margin: 10px;">
	<input placeholder="Select date" type="date" name="pickDateTo" value="<%=dateTo %>"
		class="form-control"><!--  <label for="example">Try me...</label> -->
</div>

