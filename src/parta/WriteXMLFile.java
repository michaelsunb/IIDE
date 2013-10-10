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
 
public class WriteXMLFile {
	private static WriteXMLFile instance = null;
	private static String path;

	private static String contact;
	private static String email;
	private static String description;
	private static String folder;
	private static String patentname;

	protected WriteXMLFile()
	{
		// Exists only to defeat instantiation.
	}

	public static WriteXMLFile main(String ipath)
	{
		path = ipath;
		
		if(instance == null)
		{
			instance = new WriteXMLFile();
		}
		return instance;
	}
	
	public void setContact(String icontact)
	{
		contact = icontact;
	}
	
	public void setEmail(String iemail)
	{
		email = iemail;
	}
	
	public void setDescription(String idescription)
	{
		description = idescription;
	}
	
	public void setFolder(String izipfilename)
	{
		folder = izipfilename;
	}
	
	public void setPatentName(String ipatentname)
	{
		patentname = ipatentname;
	}
	
	public String getPatentName()
	{
		return patentname;
	}
	
	public boolean doit()
	{
		Document doc = null;
	  try {
		File xmlFile = new File(path + File.separator + "metadata.xml");

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
			Element rootElement = doc.createElement("metadata");
			doc.appendChild(rootElement);
		}
		catch (IOException e)
		{
			//e.printStackTrace();
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("metadata");
			doc.appendChild(rootElement);
		}

		NodeList rootElementNode = doc.getElementsByTagName("metadata");
		Node root = rootElementNode.item(0);

		// efolder elements
		Element efolder = doc.createElement("folder");
		root.appendChild(efolder);
 
		// set attribute to efolder element
		Attr attr = doc.createAttribute("folder-name");
		attr.setValue(folder);
		efolder.setAttributeNode(attr);
 
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

		Element epatentname = doc.createElement("patent-name");
		epatentname.appendChild(doc.createTextNode(patentname));
		efolder.appendChild(epatentname);

		Element edescription = doc.createElement("description");
		edescription.appendChild(doc.createTextNode(description));
		efolder.appendChild(edescription);

		Element econtact = doc.createElement("contact");
		econtact.appendChild(doc.createTextNode(contact));
		efolder.appendChild(econtact);

		Element eemail = doc.createElement("email");
		eemail.appendChild(doc.createTextNode(email));
		efolder.appendChild(eemail);
 
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