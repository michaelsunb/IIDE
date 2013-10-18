package doc;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * return an arraylist of each document info for listing all the patent metadata
 * 
 * email: s3440760@student.rmit.edu.au
 * @author Yongjiang Zhang
 *
 */

public class docList {
	public List<Docs> Read(String ipath){

		List<Docs> lst = new ArrayList<Docs>();

		try {
			//TODO file path is hard-codded here. Will be changed before presentation. 
			File fXmlFile = new File(ipath + "/data/doc.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 		 
			NodeList nList = doc.getElementsByTagName("document");

			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					//put each xml element into one Doc object
					Element eElement = (Element) nNode;
					Docs oneDoc = new Docs();

					oneDoc.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
					oneDoc.setDate(eElement.getElementsByTagName("date").item(0).getTextContent());
					oneDoc.setKeywords(eElement.getElementsByTagName("keywords").item(0).getTextContent());
					oneDoc.setApplicant(eElement.getElementsByTagName("applicant").item(0).getTextContent());
					oneDoc.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
				
					//add each Doc object into an arraylist
					lst.add(oneDoc);
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  
		return lst;
	}
//  public static void main(String argv[]) {
//	List<Docs> lst = new docList().Read();
//	Docs doclist = lst.get(0);
//	String title = doclist.getTitle();
//	System.out.println(title);
//
//  }
 
    
 
}

