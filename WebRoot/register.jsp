<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%java.util.Date d = new java.util.Date();   
java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyMMddhhmmssSSS");   
String id = dformat.format(d);  
 %>
<%@include file="header.jsp" %>
      <center>
    <form action="servlet/addUserServlet" method="post">

  <table width="70%" border="1" cellspacing="0" cellpadding="0" id="main_table">
   <tr class="trTitle">
    <td colspan="2" align="center">NEW USER</td>
    </tr>
  <tr>
    <td>Username</td>
    <td>
      <input type="text" name="Username" id="Username">
   </td>
  </tr>
  <tr>
    <td>Password</td>
    <td><input type="password" name="Pwd" id="Pwd"></td>
  </tr>
  <tr>
    <td>User Type</td>
    <td> <select name="sltRole" id="sltRole">
  <option>admin</option>
  <option>registered user</option>
  <option>visitor</option>
    <option>staff</option>
  </select></td>
  </tr>
  <tr>
    <td>Name</td>
    <td>
      <input type="text" name="Name" >
   </td>
  </tr>
  <tr>
    <td>Gender</td>
    <td> <select name="Gender" id="Gender">
  <option>Female</option>
  <option>Male</option>
  </select></td>
  </tr>
  <tr>
    <td>Country of Living</td>
    <td>
      <input type="text" name="Living" >
   </td>
  </tr>
  <tr>
    <td>Country of Applying</td>
    <td>
      <input type="text" name="Apply" >
   </td>
  </tr>
  <tr>
    <td>Address</td>
    <td>
      <input type="text" name="Address" >
   </td>
  </tr>
  <tr>
    <td>Phone</td>
    <td>
      <input type="text" name="Phone" >
   </td>
  </tr>
  <tr>
    <td>Email</td>
    <td>
      <input type="text" name="Email" >
   </td>
  </tr>
  
    <tr>
    <td colspan="2" align="center">
	  <input type="submit" value="submit"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="reset" value="reset"/></td>
  </tr>
</table>
</form>
 
   </center>
  </body>
</html>
