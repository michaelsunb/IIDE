<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="header.jsp" %>
<%@page import="doc.docList" %>
<%@page import="doc.Docs" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%
/**
 * Showing all patent documents in a list depending the records from doc.xml
 * 
 * email: s3440760@student.rmit.edu.au
 * @author Yongjiang Zhang
 *
 */ 
 %>

      <center>
      <% 
      //generate an arraylist from the doc.xml
      List<Docs> lst = new docList().Read();
       %>
  <table width="70%" border="1" cellspacing="0" cellpadding="0" id="main_table">
   <tr class="trTitle">
    <td colspan="6" align="center">Documents List</td>
    </tr>
  <tr>
    <th>Title</th>
    <th>Keywords</th>
    <th>Applicant</th>
    <th>Submit Date</th>
    <th>Description</th>
        <th>Content</th>
  </tr>
  <% 
  //traverse all document elements in the arraylist
  for (int i=0; i<lst.size();i++ ) {
  Docs doclist = lst.get(i);
	  String title = doclist.getTitle();
	  String keywords = doclist.getKeywords();
	  String applicant = doclist.getApplicant();
	  String date = doclist.getDate();
	  String description = doclist.getDescription();

   %>
  <tr>
    <td><%=title %></td>
    <td><%=keywords %></td>
    <td><%=applicant %></td>
    <td><%=date %></td>
    <td><%=description %></td>
    <%//document link is related with document ID 
 		//only admin and registered user can see go to this link, 
 		//the usertype is sent with get method  
 		HttpSession session2 = request.getSession();
				String Usertype = (String)session2.getValue("usertype");
				
    %>
        <td><a href="servlet/XML2HTMLServlet?docID=<%=i+1%>&usertype=<%=Usertype%>">Details</a></td>
  </tr>
    <% } %>
</table>
 
   </center>
  </body>
</html>
