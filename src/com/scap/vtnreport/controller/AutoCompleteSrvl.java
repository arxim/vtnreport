package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.scap.vtnreport.dao.AutoCompleteDao;;

/**
 * Servlet implementation class AutoCompleteSrvl
 */
@WebServlet("/AutoCompleteSrvl")
public class AutoCompleteSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoCompleteSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = new JSONArray();
		AutoCompleteDao autocomplete = new AutoCompleteDao();
		
		String hospitalCode = request.getParameter("hospitalCode");
		String doctorSearch = request.getParameter("doctorSearch");
		String countySearch = request.getParameter("countySearch");
		String type=request.getParameter("type");
		System.out.println(type);
		try {
			if(type.equals("COUNTY")) {
				jsonArr = autocomplete.lookupCounty(countySearch, hospitalCode);
			}else {
				jsonArr = autocomplete.lookupDoctor(doctorSearch, hospitalCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(jsonArr.toString());
		out.println(jsonArr.toString());
	}

}
