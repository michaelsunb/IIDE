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
		<input type="text" size="50" name="title" id="title" value="Test Title">
		<br />
		Keyword:
		<br/>
		<input type="text" size="50" name="keyword" id="keyword" value="Test Keyword">
		<br />
		Date:
		<br/>
		<input type="text" size="50" name="date" id="date" value="Test Date">
		<br />
		Description:
		<br/>
		<input type="text" size="50" name="description" id="description" value="Test Description">
		<br />
		Zip File:
		<br/>
		<input type="file" size="50" name="file" id="file">
		<br/>
		<input type="submit" value="Upload">
	</form>
</body>
</html>