import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doc.docList;

/**
 * Servlet to check login details - hard-coded here.
 * 
 * email: s3440760@student.rmit.edu.au
 * @author Yongjiang Zhang
 *
 */

@WebServlet("/loginCheck")
public class loginCheck extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public loginCheck() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * It should check username and password validation against user.xml
	 * By now, i just check three hard-coded user info and put them into session
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//To simplify the login system, without write a user xml file,
		//only three user account allow here.
		if(username!=null){
			if(username.equals("admin") && password.equals("admin")){
				HttpSession session = request.getSession();
				session.setAttribute("usertype", "admin");
				session.setAttribute("username", "admin");
				
			}else if(username.equals("regUser") && password.equals("regUser")){
				HttpSession session = request.getSession();
				session.setAttribute("usertype", "registed");
				session.setAttribute("username", "regUser");

			} else if(username.equals("visitor") && password.equals("visitor")){
				HttpSession session = request.getSession();
				session.setAttribute("usertype", "visitor");
				session.setAttribute("username", "visitor");

			}
		}
		response.sendRedirect("../login.jsp");
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}

}
