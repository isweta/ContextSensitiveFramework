package com.verizon;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB 
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String SAVE_DIR = "uploadFiles";
    
    public UploadServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        String appPath = request.getServletContext().getRealPath("/");
        System.out.println("ap path is:"+appPath);
        String savePath = appPath + File.separator + SAVE_DIR;
		
		
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String fileName=null ;
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
        	fileName = extractFileName(part);
        	 // part.write(savePath +File.separator+ fileName);
           part.write(fileName);
        }
        System.out.println(fileName);
       
		String filePath=ReadWriteTextFileJDK7.mainReadWrite(fileName, savePath);
		HttpSession session=request.getSession();
		session.setAttribute("filePath", filePath);
        request.setAttribute("message", "Upload has been done successfully!");
        getServletContext().getRequestDispatcher("/FieldsListServlet").forward(
                request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
            	//(s.indexOf('=') + 1).trim().replace("\"", "")
            	//actual (s.indexOf("=") + 2, s.length()-1)
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
