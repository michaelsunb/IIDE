package user;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 
public class userlist {
	public String Read(){
		String result = "";
		try {
			 
			File fXmlFile = new File("/Library/Tomcat/apache-tomcat-8.0.0-RC1/webapps/IIDE/data/user.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
		//	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		 
			NodeList nList = doc.getElementsByTagName("user");
		 
//			System.out.println("----------------------------");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
			//	System.out.println("\nCurrent Element :" + nNode.getNodeName());
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					result +=  "Username: " + eElement.getElementsByTagName("username").item(0).getTextContent() + "\n";		 
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  
		return result;
	}
//  public static void main(String argv[]) {
//	  System.out.print(new userlist().Read());
//  }
 
    
 
}

