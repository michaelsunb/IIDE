import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * @author robh; The code is a copyright of 'http://www.java2s.com/Code/Java/XML/SimpleexampleofusingJDOM.htm'
 * It has been used for the academic-purposes only
 *  
 */
public class JDOMOutputter {

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
    	FileWriter fw = new FileWriter("WebRoot/data/DOC00001.html");
		BufferedWriter bw = new BufferedWriter(fw);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build("WebRoot/data/DOC00001.xml");
        System.out.println(doc.toString());
        StringBuffer output = new StringBuffer();
        StringBuffer output1 = new StringBuffer();
        // create the basic HTML output      
        output.append("<html>\n");
        output.append("<head>\n<title>\nPatent\n</title>\n</head>\n");
        output.append("<body>\n");
        output.append("<div>\n");
        Element root=doc.getRootElement();
        System.out.println(root.toString());
       
        List attributes=root.getAttributes();
        Iterator iterator=attributes.iterator();
        
        while (iterator.hasNext()) {
        
        	Attribute attribute = (Attribute) iterator.next();
        	 
            output.append("<a id="+attribute.getName()+">");
//            output.append(attribute.getName());
//            output.append(":");
            output.append(attribute.getValue());
            output.append("</a>\n");
        }
        output.append("</div><hr>\n");
        bw.write(output.toString());
       List<Element> elms= new ArrayList<Element>();
       elms = root.getChildren();
//       System.out.println(elms.size());
       for(int i=0;i<elms.size();i++){    
    	   Element elm=(Element)elms.get(i);
    	   
           output1.append("<div>\n");
           output1.append("<h2>");
           output1.append(elm.getName());
           output1.append("\n</h2>\n");
           if(elm.getChildren()!=null){
        	   Iterator<Element> iter= elm.getChildren().iterator();
        	   while(iter.hasNext()){
        		   Element subelm=(Element) iter.next();
        		   output1.append("<p>\n");
        		   output1.append(subelm.getValue());
            	   output1.append("\n</p>");      		   
        	   }
        	}
        	 else{      	   
        	   output1.append("<p>\n");
        	   output1.append(elm.getValue());
        	   output1.append("\n</p>");
             }
       }     
       output1.append("</div>\n<hr>");                
       // create the end of the HTML output
        output1.append("</body>\n</html>");
        System.out.println(output.toString().length());
        System.out.println(output1.toString().length());
       
       bw.write(output1.toString());
    }
}