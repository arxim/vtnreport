package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.quartz.SchedulerException;

import com.scap.vtnreport.service.SentEmailNewService;

/**
 * Servlet implementation class CheckSchedulerSrvl
 */
@WebServlet("/CheckSchedulerSrvl")
public class CheckSchedulerSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSchedulerSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		SentEmailNewService sentEmail = new SentEmailNewService();
		JSONArray jsonArr = new JSONArray();
		String val = "";
		try {
			jsonArr = sentEmail.isCheckRunning();
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(jsonArr.getString(0));
		out.print(jsonArr.toString());
		out.flush();
	}

}
