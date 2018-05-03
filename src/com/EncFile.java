package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class EncFile extends HttpServlet {

	public int parseInt(String s){
		int a = Integer.parseInt(""+s.charAt(0), 16);
		int b = Integer.parseInt(s.substring(1),16);
		return (a<<28) ^ b;
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		PrintWriter out = response.getWriter();
//		
//		out.flush();
//		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String model = request.getParameter("model");
		String fileName = request.getParameter("fileName");
		String file_ = request.getParameter("fileStr");
		String fileKey = request.getParameter("fileKey");

		String path = this.getServletConfig().getServletContext().getRealPath("/");
		File f = new File(path+"files/",fileName+".sm4");
		f.createNewFile();
		FileOutputStream out = new FileOutputStream(f);
		int[] key = new int[4];
		for(int i=0; i<4; i++){
			key[i] = parseInt(fileKey.substring(i*8, i*8+8));
		}
		file_ = file_.split("base64,")[1];
		out.write(SM4.sm4enc(Base64.getDecoder().decode(file_),key));
		out.close();
		
		HashMap<String,String> hm = new HashMap<>();
		hm.put("fileLink", "/pro1/files/"+fileName+".sm4");
		PrintWriter ot = response.getWriter();
		ot.write(JSONArray.fromObject(hm).toString());
		ot.flush();
		ot.close();
	}

}
