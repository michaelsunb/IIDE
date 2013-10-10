package partc;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class DocumentXML {

	/**
	 * @param args
	 */
	public void BuildXMLDoc(List<Documents> lst,String path){
		Element root = new Element("documents");    
		Document myDocument = new Document(root);
		Iterator<Documents> iter = lst.iterator();
		int id = 1;
		while(iter.hasNext()){
			Documents doc = new Documents();
			doc = iter.next();
			String title = doc.getTitle();
			String keywords = doc.getKeywords();
			String applicant = doc.getApplicant();
			String date = doc.getDate();
			String description = doc.getDescription();
			Element docElement = new Element("document");
			docElement.setAttribute("id", "DOC0000"+id);
			
			Element e_title = new Element("title");
			e_title.addContent(title);
			docElement.addContent(e_title);
			Element e_keywords = new Element("keywords");
			e_keywords.addContent(keywords);
			docElement.addContent(e_keywords);
			Element e_applicant = new Element("applicant");
			e_applicant.addContent(applicant);
			docElement.addContent(e_applicant);
			Element e_date = new Element("date");
			e_date.addContent(date);
			docElement.addContent(e_date);
			Element e_description = new Element("description");
			e_description.addContent(description);
			docElement.addContent(e_description);
			root.addContent(docElement);
			id++;
		}
			Format format = Format.getPrettyFormat(); 
			XMLOutputter XMLOut = new XMLOutputter(format); 
	        try {
				XMLOut.output(myDocument, new FileOutputStream(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
	}
	
	//test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Documents doc = new Documents();
		doc.setTitle("1");
		doc.setDate("1");
		doc.setKeywords("1");
		doc.setApplicant("1");
		doc.setDescription("1");
		Documents doc1 = new Documents();
		doc1.setTitle("2");
		doc1.setDate("2");
		doc1.setKeywords("2");
		doc1.setApplicant("2");
		doc1.setDescription("2");
		List<Documents> lst = new ArrayList<Documents>();
		lst.add(doc);
		lst.add(doc1);
		new DocumentXML().BuildXMLDoc(lst, "WebRoot/data/doc.xml");
	}

}
