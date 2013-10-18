<%@include file="header.jsp" %>
<%@page import="doc.docList" %>
<%@page import="doc.Docs" %>
<%
List<Docs> lst = new docList().Read();
		Docs doclist = lst.get(1);
	  String title = doclist.getTitle();
	  String keywords = doclist.getKeywords();
	  String applicant = doclist.getApplicant();
	  String date = doclist.getDate();
	  String description = doclist.getDescription();
%>
<%=title%>
</body>
</html>