<%@include file="header.jsp" %>

<%		String loginName =  (String)session.getAttribute("username");
		String Usertype =  (String)session.getAttribute("usertype");
		
		//welcome page for login user
        if(loginName!=null)
            {
            out.println("<p style='text-align:center;''>");
             out.println("Welcome  "+loginName+"!<br /> Your user type is: " +Usertype+ "<br />You can logout <a href=\"logout.jsp\" >Here</a>");
             out.println("</p>");
            }
        else 
            {
            %>

    <form action="servlet/loginCheck" method="post">
      <table width="40%" border="0" align="center" cellpadding="0"
      cellspacing="0">
        <tr>
          <td align="center" valign="middle">username: </td>

          <td><input type="text" size="16" maxlength="16" name=
          "username" /></td>
        </tr>

        <tr>
          <td align="center" valign="middle">password: </td>

          <td><input type="password" size="16" maxlength="16" name=
          "password" /></td>
        </tr>

        <tr>
        <td></td>
          <td align="left" valign="middle" style="padding-top:10px;"><input type="submit"
          value="login" /> <input type="reset" value="reset" /></td>
        </tr>
      </table>
    </form>
    <% }%>

</body>
</html>
