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

import doc.Docs;

/**
 * write data into doc.xml
 * 
 * email: s3440760@student.rmit.edu.au
 * @author Yongjiang Zhang
 *
 */

public class DocumentXML {

	/**
	 * @param args
	 */
	public void BuildXMLDoc(List<Docs> lst,String path){
		Element root = new Element("documents");    
		Document myDocument = new Document(root);
		Iterator<Docs> iter = lst.iterator();
		int id = 1;
		while(iter.hasNext()){
			Docs doc = new Docs();
			doc = iter.next();
			//read the attributes value first
			String title = doc.getTitle();
			String keywords = doc.getKeywords();
			String applicant = doc.getApplicant();
			String date = doc.getDate();
			String description = doc.getDescription();
			//set an id for each document
			Element docElement = new Element("document");
			docElement.setAttribute("id", "DOC0000"+id);
			
			//set values
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
	        	//write into file by using XMLOutputter
				XMLOut.output(myDocument, new FileOutputStream(path));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			
	}
	
	public static void main(String[] args) {
		//testing for doc.xml write		
		Docs doc = new Docs();
		doc.setTitle("1");
		doc.setDate("1");
		doc.setKeywords("1");
		doc.setApplicant("1");
		doc.setDescription("1");
		Docs doc1 = new Docs();
		doc1.setTitle("2");
		doc1.setDate("5");
		doc1.setKeywords("3");
		doc1.setApplicant("4");
		doc1.setDescription("6");
		List<Docs> lst = new ArrayList<Docs>();
		lst.add(doc);
		lst.add(doc1);
		new DocumentXML().BuildXMLDoc(lst, "WebRoot/data/doc.xml");
	}

}
