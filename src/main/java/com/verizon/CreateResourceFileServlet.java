package com.verizon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateResourceFileServlet
 */
@WebServlet("/CreateResourceFileServlet")
public class CreateResourceFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateResourceFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    final static Charset ENCODING = StandardCharsets.UTF_8;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String appPath = request.getServletContext().getRealPath("/");
        String savePath = appPath + File.separator + "uploadFiles";
		String OUTPUT_FILE_NAME=new File(savePath, "resource.js").getPath();
		List<String> lines=new ArrayList<String>();
		StringBuffer buff=new StringBuffer();
		buff.append("\ngOptions={");
		lines.add("gOptions={");
		 Path path = Paths.get(OUTPUT_FILE_NAME);
	     System.out.println(path);
	     HttpSession session=request.getSession();
	   int noIds=(Integer)(session.getAttribute("noIds"));
	   String filePath=(String)session.getAttribute("filePath");
	   List<String> ls=FieldsListRetriever.getFieldsList(filePath);
	   System.out.println(ls);
	   for(int i=0; i<noIds; i++){
		   //String idName="id_"+i;
		   String resValue=request.getParameter(ls.get(i));
		   System.out.println(resValue);
		   if(resValue!=""){
			   buff.append("\n"+ls.get(i)+":"+resValue);
			   lines.add(ls.get(i)+":"+resValue);
		   }
	   }
	   buff.append("\n}");
	   //String lines= buff.toString();
	   lines.add("}");
	   System.out.println(buff.toString());
	     
	     Files.write(path, lines, ENCODING);
	     getServletContext().getRequestDispatcher("/Downloads.jsp").forward(
	                request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
