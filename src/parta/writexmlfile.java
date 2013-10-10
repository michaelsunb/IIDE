package parta;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Creates an XML file.
 * 
 * @email: s3110401@student.rmit.edu.au
 * @author Michaelsun Baluyos
 *
 */
public class writexmlfile {
	private static writexmlfile instance = null;
	private static String path;

	private String title;
	private String date;
	private String description;
	private String keyword;
	private String validwipoxml;

	protected writexmlfile()
	{
		// Exists only to defeat instantiation.
	}

	/**
	 * Create a singleton because this class is called
	 * in several other classes.
	 * 
	 * @param ipath		path name for the web app.
	 * @return			returns the signleton class.
	 */
	public static writexmlfile main(String ipath)
	{
		if(!ipath.isEmpty())
		{
			path = ipath;
		}
		
		if(instance == null)
		{System.out.println(path);
			instance = new writexmlfile();
		}
		return instance;
	}

	/**
	 * Set the values and put them into an object.
	 * 
	 * @param ititle
	 * @param ikeyword
	 * @param idescription
	 * @param idate
	 * @param ivalidwipoxml
	 */
	public void setTitle(String ititle)
	{
		title = ititle;
	}

	public void setKeyword(String ikeyword)
	{
		keyword = ikeyword;
	}
	
	public void setDescription(String idescription)
	{
		description = idescription;
	}
	
	public void setDate(String idate)
	{
		date = idate;
	}
	
	public void setValidWIPOXml(String ivalidwipoxml)
	{
		validwipoxml = ivalidwipoxml;
	}
	
	public String getValidWIPOXml()
	{
		return validwipoxml;
	}

	/**
	 * Let's create the XML file as below:
	 * 
	 * <documents>
	 *    <document id="">
	 *       <title></title>
	 *       <keywords></keywords>
	 *       <date></date>
	 *       <description></description>
	 *    </document>
	 * </documents>
	 * 
	 */
	public void doit()
	{
		Document doc = null;

		try {
			/**
			 * create or modify doc.xml file
			 */
			File xmlFile = new File(path + File.separator + "doc.xml");
	
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			/**
			 * root elements
			 */
			try
			{
				/**
				 * We see if the xml file is already there.
				 */
				doc = docBuilder.parse(xmlFile);
			}
			catch (SAXException e)
			{
				/**
				 * If not we create a new file with new elements.
				 */
				doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("documents");
				doc.appendChild(rootElement);
			}
			catch (IOException e)
			{
				doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("documents");
				doc.appendChild(rootElement);
			}

			/**
			 * Retrieve the root element, which is documents.
			 */
			NodeList rootElementNode = doc.getElementsByTagName("documents");
			Node root = rootElementNode.item(0);

			/**
			 * create document child elements
			 */
			Element edocument = doc.createElement("document");
			root.appendChild(edocument);

			int noelements = doc.getElementsByTagName("document").getLength();

			/**
			 * create id attribute to document element
			 */
			Attr idattr = doc.createAttribute("id");
			idattr.setValue("DOC0000" + noelements);
			edocument.setAttributeNode(idattr);

			/**
			 * create filename attribute to document element
			 */
			Attr filattr = doc.createAttribute("filename");
			filattr.setValue(validwipoxml);
			edocument.setAttributeNode(filattr);

			/**
			 * create title child elements
			 */
			Element etitle = doc.createElement("title");
			etitle.appendChild(doc.createTextNode(title));
			edocument.appendChild(etitle);

			/**
			 * create keywords child elements
			 */
			Element ekeyword = doc.createElement("keywords");
			ekeyword.appendChild(doc.createTextNode(keyword));
			edocument.appendChild(ekeyword);

			/**
			 * create date child elements
			 */
			Element edate = doc.createElement("date");
			edate.appendChild(doc.createTextNode(date));
			edocument.appendChild(edate);

			/**
			 * create description child elements
			 */
			Element edescription = doc.createElement("description");
			edescription.appendChild(doc.createTextNode(description));
			edocument.appendChild(edescription);
	 
			/**
			 *  write the content into xml file
			 */
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
	 
			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}