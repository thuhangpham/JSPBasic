package nhom8.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoadFile
 */
@WebServlet("/DownLoadFile")
public class DownLoadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String filePath = getServletContext().getRealPath("")+"//filePDF.pdf"; 
	     File downloadFile = new File(filePath);
	     FileInputStream inStream = new FileInputStream(downloadFile);
	      
	     // if you want to use a relative path to context root:
	     //String relativePath = getServletContext().getRealPath(""); 
	      
	     // obtains ServletContext
	     ServletContext context = getServletContext(); 
	     String mimeType = context.getMimeType(filePath);
	     if (mimeType == null) {        
	         // set to binary type if MIME mapping not found
	         mimeType = "application/octet-stream";
	     } 
	     // modifies response 
	     response.setContentType(mimeType);
	     response.setContentLength((int) downloadFile.length());
	    
	     // forces download
	     String headerKey = "Content-Disposition";
	     String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	     response.setHeader(headerKey, headerValue); 
	     // obtains response's output stream
	     OutputStream outStream = response.getOutputStream();
	     byte[] buffer = new byte[1024*4];
	     int bytesRead = -1; 
	     while ((bytesRead = inStream.read(buffer)) != -1) {
	         outStream.write(buffer, 0, bytesRead); 
	     }
	     inStream.close();
	     outStream.close();
	}
 
}
