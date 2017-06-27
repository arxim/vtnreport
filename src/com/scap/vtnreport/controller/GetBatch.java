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

import org.json.JSONArray;

import com.scap.vtnreport.dao.BatchDao;

/**
 * Servlet implementation class GetBatch
 */
@WebServlet("/GetBatch")
public class GetBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBatch() {
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
		BatchDao data = new BatchDao();
		String method = request.getParameter("method");
		System.out.println("Method : "+method);
		try {
			
			switch (method) {
			//Get Batch is Open
			case "01":
				jsonArr = data.getBatch();
				break;
			// Get Batch is Last Close
			case "02":
				jsonArr = data.getLastBatchOnClose();
				break;
			// Get Tax 406 Last Close
			case "03":
				jsonArr = data.getLastBatchTax406OnClose();
				break;
			default:
				break;
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		System.out.println(jsonArr.toString());
		out.println(jsonArr.toString());
		
	}

}
