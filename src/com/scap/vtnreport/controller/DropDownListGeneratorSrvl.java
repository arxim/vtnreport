package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scap.vtnreport.utils.DbConnector;
import com.scap.vtnreport.utils.ReadProperties;

 

/**
 * Servlet implementation class DropDownListGeneratorSrvl
 */
@WebServlet("/DropDownListGeneratorSrvl")
public class DropDownListGeneratorSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DropDownListGeneratorSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("application/html");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        
	        ArrayList<HashMap<String,String>> dropdown = new ArrayList<HashMap<String,String>>();
	        String list = "";
	    	String options = "";
	    	
	        if(request.getParameter("tb").equals("USERS")){
	            list = "SELECT '' AS CODE, '--- เลือกพนักงาน ---' AS NAME FROM USERS "+
	         		   "UNION "+
	            	   "SELECT USER_ID AS CODE, NAME AS NAME FROM USERS";
	        }else if(request.getParameter("tb").equals("USER_ROLE")){
	        	list = "SELECT '' AS CODE, '--- รายละเอียดสิทธิ์ผู้ใช้งาน ---' AS NAME FROM USER_ROLE "+
	        		   "UNION "+
	        		   "SELECT ROLE_ID AS CODE, ROLE_NAME AS NAME FROM USER_ROLE ORDER BY CODE";
	        }else {
	        	
	        }
	        
	        if(request.getParameter("tb").equals("HOSPITAL_LOGIN")){
	        	ReadProperties prop = new ReadProperties();
	        	dropdown = prop.getDataObjReadPropertiesFile("hospital.properties");
	        	
	        } else {
	            DbConnector db = new DbConnector();
				db.doConnect();
				dropdown = db.getData(list);
				db.doDisconnect();
	        }
	        for(int i = 0; i<dropdown.size(); i++){
	        	options += "<option value=\""+dropdown.get(i).get("CODE")+"\"> "+dropdown.get(i).get("NAME")+" </option>";
	        }
	       
	    	out.println(options);
	    	out.close();
	}
}
