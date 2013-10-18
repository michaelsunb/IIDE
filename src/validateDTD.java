

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.*;

import org.apache.commons.io.FileUtils;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 * Class that checks a zip file for a valid XML against
 * a DTD file (ep-patent-document-v1-0.dtd) within /upload/
 * 
 * @email: s3110401@student.rmit.edu.au
 * @author Michaelsun Baluyos
 *
 */
public class validateDTD  extends DefaultHandler
{
	private static String path = "";
	private static ZipFile zipFile;

	private boolean validWIPO = false;

	public validateDTD(){}

	/**
	 * Call this function to start the validation. This function
	 * will check if a zip file has a xml file thats validates
	 * against ep-patent-document-v1-0.dtd dtd
	 * 
	 * @param ipath			folder path of a zip file.
	 * @param filename		file name of the above zip file.
	 * @return boolean		returns true or false if contents
	 * 						zip file is valid against
	 * 						ep-patent-document-v1-0.dtd
	 * @throws IOException
	 */
	public static boolean main(String ipath, String filename) throws IOException 
	{
		path = ipath;
		
		zipFile = new ZipFile(path + File.separator  + filename);

		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		validateDTD handler = new validateDTD();

		/**
		 * First check every file in the zip file.
		 */
		boolean isDTDValid = handler.checkfile(entries);
		if(isDTDValid)
		{

			/**
			 * Call zipFile.entries() again for while loop.
			 */
			entries = zipFile.entries();
			while(entries.hasMoreElements())
			{
				ZipEntry entry = entries.nextElement();

				String entryFileName = entry.getName();

				/**
				 * Check if file extensions is a txt, pdf, tif,
				 * jpeg, jpg or xml.
				 */
				if(validateDTD.isfileExt(entryFileName,".txt") || 
					validateDTD.isfileExt(entryFileName,".pdf")||
					validateDTD.isfileExt(entryFileName,".tif")||
					validateDTD.isfileExt(entryFileName,".jpeg")||
					validateDTD.isfileExt(entryFileName,".jpg")||
					validateDTD.isfileExt(entryFileName,".png")||
					validateDTD.isfileExt(entryFileName,".xml"))
				{
					handler.unzipFile(entry,filename);
				}
			}
		}

		zipFile.close();

		return isDTDValid;
	}

	/**
	 * Lowercase's the a filename extension name and checks if
	 * the extension is what is chosen. 
	 * 
	 * @param filename		the file name used to be checked.
	 * @param ext			the file extension used to checked
	 * 						against the file name.
	 * @return boolean		returns true or false if the file
	 * 						extension is what you wanted.
	 * @throws IOException
	 */
	public static boolean isfileExt(String filename, String ext) throws IOException 
	{
		String lowFileExt = filename.substring(0,filename.length() - 3) + 
				/**
				 * lower case the extension
				 */
				filename.substring(filename.length() - 3).toLowerCase();

		return lowFileExt.endsWith(ext);
	}

	/**
	 * Checks the files within a zip file has a valid xml file against
	 * ep-patent-document-v1-0.dtd dtd and if the file extensions are 
	 * txt or pdf document or tif, jpeg, jpg, or png images.
	 * 
	 * @param entries		Entries of a zip file.
	 * @return boolean		Returns true of false if files are agreed
	 * @throws IOException
	 */
	private boolean checkfile(Enumeration<? extends ZipEntry> entries) throws IOException 
	{
		boolean isValid = false;

		while(entries.hasMoreElements())
		{
			ZipEntry entry = entries.nextElement();

			String entryFileName = entry.getName();

			if(validateDTD.isfileExt(entryFileName,".xml"))
			{
				/**
				 * If xml file is valid we don't need to check
				 * the other xml's.
				 */
				if(!validWIPO)
				{
					this.tempUnzipFile(entry);
				}

				isValid = true;
			}
			else if(validateDTD.isfileExt(entryFileName,".txt") || 
					validateDTD.isfileExt(entryFileName,".pdf")||
					validateDTD.isfileExt(entryFileName,".tif")||
					validateDTD.isfileExt(entryFileName,".jpeg")||
					validateDTD.isfileExt(entryFileName,".jpg")||
					validateDTD.isfileExt(entryFileName,".png"))
			{
				isValid = true;
			}
		}

		if(!validWIPO)
		{
			return false;
		}

		return isValid;
	}

	/**
	 * Unzip's one file at a time to /upload/(filename of zip file) 
	 * 
	 * @param entry			ZipEntry of an approved file in the zip file.
	 * @param zipfilename	Name of the the zip file to create the folder
	 * 						in /upload/
	 * @return void
	 * @throws IOException
	 */
	private void unzipFile(ZipEntry entry, String zipfilename) throws IOException 
	{
		String name = entry.getName();
		/**
		 * Remove the zip file extension name and
		 * create a folder using the zip name only.
		 */
		String foldername = zipfilename.substring(0,zipfilename.length() - 4);

		String fullfilename = path + File.separator  + 
				foldername + 
				File.separator  + name;
		File file = new File(fullfilename);

		if (file.exists())
		{
			return;
		}

		this.writefile(entry, file);
	}

	/**
	 * Temporary unzip's a file to check against
	 * ep-patent-document-v1-0.dtd dtd
	 * 
	 * @param entry			ZipEntry of a file in the zip file.
	 * @return void
	 * @throws IOException
	 */
	private void tempUnzipFile(ZipEntry entry) throws IOException 
	{
		String name = entry.getName();

		String fullfilename = path + File.separator  + name;

		File file = new File(fullfilename);

		this.writefile(entry, file);

		validWIPO = this.parseFile(fullfilename);

		if(validWIPO)
		{
			writexmlfile.main("")
				.setValidWIPOXml(name.substring(0,name.length() - 4));
		}

		file.delete();
	}

	/**
	 * Temporary copy a file nd check against
	 * ep-patent-document-v1-0.dtd dtd
	 * 
	 * @param filename		file name of file to be copied
	 * @param foldername	folder name where the file is.
	 * @param path			path of the foler and file
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean tempCopyFile(String filename,String foldername,String path) throws IOException 
	{
		String tempfilename = path + File.separator + filename;
		String fullfilename = path + File.separator + foldername + File.separator + filename;

		File fileinfolder = new File(fullfilename);
		File tempfile = new File(tempfilename);

		validateDTD handler = new validateDTD();
		handler.copyfile(fileinfolder, tempfile);

		boolean validWIPO = handler.parseFile(tempfilename);

		if(!validWIPO)
		{
			tempfile.delete();
		}
		
		return validWIPO;
	}

	/**
	 * Parses and validates a file with DocumentBuilder.
	 * 
	 * @param fullfilename	File name including its path
	 * @return boolean		Returns if valid against
	 * 						ep-patent-document-v1-0.dtd dtd
	 * @throws IOException 
	 */
	private boolean parseFile(String fullfilename) throws IOException
	{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

		/**
		 * Set true to validates against DTD
		 */
		domFactory.setValidating(true);
		domFactory.setNamespaceAware(true);

		InputStream stream = null;
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();

			/**
			 * Needed to capture the errors
			 */
			builder.setErrorHandler(new SimpleErrorHandler());
			builder.setEntityResolver(new EntityResolver() {
			    @Override
			    public InputSource resolveEntity(String publicId, String systemId)
			            throws SAXException, IOException {
			    	return new InputSource(new FileReader(path + File.separator + "ep-patent-document-v1-0.dtd"));
			    }
			});

			/**
			 * Start parsing
			 */
			stream = new FileInputStream(fullfilename);
			builder.parse(stream);
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		} finally {
            if(stream != null){
                stream.close();
            }
		}
		return true;
	}

	/**
	 * Common write file that will close after writing.
	 * 
	 * @param entry			ZipEntry of an file in the zip file.
	 * @param file			File to be written.
	 * @return void
	 * @throws IOException
	 */
	private void writefile(ZipEntry entry, File file) throws IOException
	{
		String name = entry.getName();

		if (name.endsWith("/"))
		{
			file.mkdirs();
		}

		File parent = file.getParentFile();
		if (parent != null)
		{
			parent.mkdirs();
		}

		/**
		 * Let's write this!
		 */
		InputStream is = zipFile.getInputStream(entry);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			try {
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) >= 0)
				{
					fos.write(bytes, 0, length);
				}
			} finally {
				fos.close();
			}
		} finally {
			is.close();
		}
	}

	/**
	 * Common copy file.
	 * 
	 * @param fileinfolder			ZipEntry of an file in the zip file.
	 * @param tempfilename			File to be written.
	 * @return void
	 * @throws IOException
	 */
	private void copyfile(File fileinfolder, File tempfilename) throws IOException
	{
		File parent = fileinfolder.getParentFile();
		if (parent != null)
		{
			parent.mkdirs();
		}
		
		FileUtils.copyFile(fileinfolder, tempfilename);
	}
}
