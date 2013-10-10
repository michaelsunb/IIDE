<%@include file="header.jsp" %>
<%@page import="user.userlist" %>
<%
	user.userlist usrlist = new user.userlist();
	//System.out.println(request.getRealPath("/"));
	
	String str = usrlist.Read();
	System.out.println(str);
	
%>
<%=str%>
</body>
</html>