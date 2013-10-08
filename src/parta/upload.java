package parta;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * upload servlet that will only temporary upload one 
 * file at a time and only a zip file to /upload/
 * 
 * email: s3110401@student.rmit.edu.au
 * @author Michaelsun Baluyos
 *
 */
@WebServlet("/upload")
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 20; // 20MB
	private static final int REQUEST_SIZE = 1024 * 1024 * 30; // 30MB
       
 	/**
	 * Constructor
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public upload() {
		super();
 	}

	/**
	 * Only needs to post so go to doPost function
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * Checks if a zip file is being uploaded and then validate it.
	 * Once validated delete uploaded file.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/**
		 *  Checks if the request actually contains upload file.
		 */
		if (!ServletFileUpload.isMultipartContent(request))
		{
			response.sendRedirect("index.jsp");
			return;
		}

		/**
		 *  Configures some settings.
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(REQUEST_SIZE);

		/**
		 *  Constructs the directory path to store upload file.
		 */
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

		boolean isDTDValid =  false;

		try {
			/**
			 *  Parses the request's content to extract file data.
			 */
			List<FileItem> formItems = upload.parseRequest(request);

			if(formItems.size() != 1)
			{
				response.sendRedirect("index.jsp");
				return;
			}

			Iterator<FileItem> iter = formItems.iterator();

			/**
			 *  Iterates over form's fields.
			 */
			while (iter.hasNext())
			{
				FileItem item = (FileItem) iter.next();
				/**
				 *  Processes only fields that are not form fields.
				 */
				if (!item.isFormField())
				{
					String fileName = new File(item.getName()).getName();

					/**
					 * Only upload and valid a zip file.
					 */
					if(validateDTD.isfileExt(fileName,".zip"))
					{
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);

						/**
						 *  Saves zip file on disk and then delete
						 *  the file after finishing checking.
						 */
						InputStream in = item.getInputStream();
						try {
							OutputStream out = new FileOutputStream(storeFile);
							try {
								byte buf[] = new byte[4096];
								for (int n = in.read(buf); n > 0; n = in.read(buf))
								{
									out.write(buf, 0, n);
								}
							} finally {
								out.close();
							}
						} finally {
							in.close();
						}

						/**
						 * Call ValidateDTD function and returns
						 * true or false if the XML is valid.
						 */
						isDTDValid = validateDTD.main(uploadPath,fileName);

						/**
						 * Delete zip file for storage purposes.
						 */
						if (storeFile.exists())
						{
							storeFile.delete();
						}
					}
				}
			}

			/**
			 * Create a message if Zip file is uploaded
			 */
			if(isDTDValid)
			{
				request.setAttribute("message", "XML is valid and has been uploaded successfully!");
				request.setAttribute("messagecolour", "green");
			}
			else
			{
				request.setAttribute("message", "Upload was not successful.");
				request.setAttribute("messagecolour", "red");
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
			request.setAttribute("message", "Upload was not successful.");
			request.setAttribute("messagecolour", "red");
		}
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
	}
}
