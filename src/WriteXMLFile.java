


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
 
public class WriteXMLFile {
	private static WriteXMLFile instance = null;
	private static String path;

	private String title;
	private String date;
	private String description;
	private String keyword;
	private String validwipoxml;

	//private String[] errormsg;

	protected WriteXMLFile()
	{
		// Exists only to defeat instantiation.
	}

	public static WriteXMLFile main(String ipath)
	{
		if(!ipath.isEmpty())
		{
			path = ipath;
		}
		
		if(instance == null)
		{System.out.println(path);
			instance = new WriteXMLFile();
		}
		return instance;
	}
	
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
	
	public boolean doit()
	{
		Document doc = null;

		/*if(errormsg.length != 0)
		{
			for(String emsg : errormsg)
			{
				System.out.println(emsg);
			}
			return false;
		}*/

		try {
			File xmlFile = new File(path + File.separator + "doc.xml");
	
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			try
			{
				doc = docBuilder.parse(xmlFile);
			}
			catch (SAXException e)
			{
				//e.printStackTrace();
				doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("documents");
				doc.appendChild(rootElement);
			}
			catch (IOException e)
			{
				//e.printStackTrace();
				doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("documents");
				doc.appendChild(rootElement);
			}
	
			NodeList rootElementNode = doc.getElementsByTagName("documents");
			Node root = rootElementNode.item(0);
	
			// efolder elements
			Element edocument = doc.createElement("document");
			root.appendChild(edocument);
	 
			// set attribute to efolder element
			Attr attr = doc.createAttribute("id");
			attr.setValue(validwipoxml);
			edocument.setAttributeNode(attr);
	 
			// shorten way
			// efolder.setAttribute("id", "1");
	 
			// firstname elements
			/*Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode("yong"));
			efolder.appendChild(firstname);

			// lastname elements
			Element lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode("mook kim"));
			staff.appendChild(lastname);*/

			Element epatentname = doc.createElement("title");
			epatentname.appendChild(doc.createTextNode(title));
			edocument.appendChild(epatentname);

			Element edescription = doc.createElement("keywords");
			edescription.appendChild(doc.createTextNode(keyword));
			edocument.appendChild(edescription);

			Element econtact = doc.createElement("date");
			econtact.appendChild(doc.createTextNode(date));
			edocument.appendChild(econtact);

			Element eemail = doc.createElement("description");
			eemail.appendChild(doc.createTextNode(description));
			edocument.appendChild(eemail);
	 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	
			return true;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		}
	}
}