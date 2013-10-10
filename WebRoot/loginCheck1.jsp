<%@include file="header.jsp" %>
   <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
                        out.println("<p style='text-align:center;''>");
           out.println("Checking login<br>");
            if (username == null || password == null) {
 
                out.print("Invalid paramters ");
            }

            if (username.toLowerCase().trim().equals("admin") && password.toLowerCase().trim().equals("admin")) {
                out.println("Welcome " + username + " <a href=\"index.jsp\">Back to Home</a>");
                session.setAttribute("username", username);
            }
           else 
               {
                out.println("Invalid username and password");
           }
             out.println("</p>");
%> 
</body>
</html>
