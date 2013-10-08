package parta;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Error Handler needed for SAXParser validation
 * 
 * email: s3110401@student.rmit.edu.au
 * @author Michaelsun Baluyos
 *
 */
public class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
		System.out.println("warning:");
        System.out.println(e.getMessage());
        System.out.println(e.toString());
        throw e;
    }

    public void error(SAXParseException e) throws SAXException {
		System.out.println("error:");
        System.out.println(e.getMessage());
        System.out.println(e.toString());
        throw e;
    }

    public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("fatalError:");
        System.out.println(e.getMessage());
        System.out.println(e.toString());
        throw e;
    }
}
