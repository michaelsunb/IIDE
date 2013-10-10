<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<form name="uploadform" id="uploadform" action="/majorproject/upload" method="post" enctype="multipart/form-data">
	<% if(request.getAttribute("message") != null)
	   {
			%><span style="color:<%= request.getAttribute("messagecolour") %>">
			<%= request.getAttribute("message") %>
			</span><%
	   } %><br />
		Contact:
		<br/>
		<input type="text" size="50" name="contact" id="contact" value="">
		<br />
		Email:
		<br/>
		<input type="text" size="50" name="email" id="email" value="">
		<br />
		Patent Name:
		<br/>
		<input type="text" size="50" name="patentname" id="patentname" value="">
		<br />
		Description:
		<br/>
		<input type="text" size="50" name="description" id="description" value="">
		<br />
		Zip File:
		<br/>
		<input type="file" size="50" name="file" id="file">
		<br/>
		<input type="submit" value="Upload">
	</form>
</body>
</html>