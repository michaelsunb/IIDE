import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class XML2HTMLServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public XML2HTMLServlet() {
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

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build("file:///Volumes/Storage/Dev/JSP/IIDE/WebRoot/upload/DOC00001.xml");
		System.out.println("test");
		TransformerFactory tFactory = TransformerFactory.newInstance();
		org.jdom.output.DOMOutputter outputter = new org.jdom.output.DOMOutputter();
		org.w3c.dom.Document domDocument = outputter.output(doc);
		javax.xml.transform.Source xmlSource = 
		  new javax.xml.transform.dom.DOMSource(domDocument);
		StreamSource xsltSource = 
		  new StreamSource(new FileInputStream("/Volumes/Storage/Dev/JSP/IIDE/WebRoot/upload/DOC00001.xsl"));
		// Make the output result for the finished document using 
		// the HTTPResponse OutputStream
		StreamResult xmlResult = new StreamResult(response.getOutputStream());
		// Get a XSLT transformer
		Transformer transformer = tFactory.newTransformer(xsltSource);
		// Do the transform
		transformer.transform(xmlSource, xmlResult);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
