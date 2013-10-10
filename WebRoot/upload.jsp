<%@include file="header.jsp" %>
	<form name="uploadform" id="uploadform" action="upload" method="post" enctype="multipart/form-data">
	<% if(request.getAttribute("message") != null)
	   {
			%><span style="color:<%= request.getAttribute("messagecolour") %>">
			<%= request.getAttribute("message") %>
			</span><%
	   } %><br />
		Title:
		<br/>
		<input type="text" size="50" name="title" id="title" value="" required="required">
		<br />
		Keyword:
		<br/>
		<input type="text" size="50" name="keyword" id="keyword" value="" required="required">
		<br />
		Date:
		<br/>
		<input type="text" size="50" name="date" id="date" value="" required="required">
		<br />
		Description:
		<br/>
		<input type="text" size="50" name="description" id="" value="" required="required">
		<br />
		Zip File:
		<br/>
		<input type="file" size="50" name="file" id="file" required="required">
		<br/>
		<input type="submit" value="Upload">
	</form>
</body>
</html>