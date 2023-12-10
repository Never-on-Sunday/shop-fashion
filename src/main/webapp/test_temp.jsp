<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="model.bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
try(PrintWriter pw = response.getWriter()){
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if(cart_list != null){
		pw.print(cart_list.size());
	}else{
		pw.print("Not received");
	}
}
%>

</body>
</html>