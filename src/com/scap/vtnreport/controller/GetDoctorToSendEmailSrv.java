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

import org.json.JSONObject;

import com.scap.vtnreport.dao.GetDoctorDao;

/**
 * Servlet implementation class GetDoctorToSendEmailSrv
 */
@WebServlet("/GetDoctorToSendEmailSrv")
public class GetDoctorToSendEmailSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDoctorToSendEmailSrv() {
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
		response.setContentType("text/html; charset=UTF-8");
		String hospitalCode = request.getParameter("hospitalCode");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		PrintWriter out = response.getWriter();
		JSONObject jsonData = null;
		

		GetDoctorDao vaEmail = new GetDoctorDao();
		try {
			jsonData = vaEmail.getDoctorDatatable(hospitalCode, yyyy, mm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(jsonData);
	}

}
