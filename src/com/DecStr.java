package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class DecStr extends HttpServlet {
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
//		String model = request.getParameter("model");
//		String str = request.getParameter("str");
//		String strKey = request.getParameter("strKey");
//		
//		int[] key = new int[4];
//		for(int i=0; i<4; i++){
//			key[i] = parseInt(strKey.substring(i*8,(i+1)*8));
//		}
//		String[] ss = str.replace("[", "").replace("]", "").split(", ");
//		byte[] M = new byte[ss.length];
//        for(int i=0; i<ss.length;i++){
//            M[i] = (byte)Integer.parseInt(ss[i]);
//        }
//		HashMap<String,String> hm = new HashMap<>();
//		hm.put("strPlaintext",new String(SM4.sm4dec(M, key)));
////		HashMap<String,String> hm = new HashMap<>();
////		hm.put("strPlaintext",new String("ajdskfj"));
//		PrintWriter out = response.getWriter();
//		out.write(JSONArray.fromObject(hm).toString());
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
		String str = request.getParameter("str");
		String strKey = request.getParameter("strKey");
		
		int[] key = new int[4];
		for(int i=0; i<4; i++){
			key[i] = parseInt(strKey.substring(i*8,(i+1)*8));
		}
		String[] ss = str.replace("[", "").replace("]", "").split(", ");
		byte[] M = new byte[ss.length];
        for(int i=0; i<ss.length;i++){
            M[i] = (byte)Integer.parseInt(ss[i]);
        }
		HashMap<String,String> hm = new HashMap<>();
		hm.put("strPlaintext",new String(SM4.sm4dec(M, key)));
//		HashMap<String,String> hm = new HashMap<>();
//		hm.put("strPlaintext",new String("ajdskfj"));
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(hm).toString());
		out.flush();
		out.close();
	}

}
