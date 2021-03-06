package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.scap.vtnreport.dao.GetDoctorDao;
import com.scap.vtnreport.utils.ReadProperties;

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
		ReadProperties prop = new ReadProperties();
		Map<String, String>  propData = prop.getDataReadPropertiesFile("servermail.properties");
		String limit_send_mail = propData.get("limit_send");
		String hospitalCode = request.getParameter("hospitalCode");
		String doctorCode = request.getParameter("doctorCode");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String report = request.getParameter("report");
		String term = request.getParameter("term");
		PrintWriter out = response.getWriter();
		JSONObject jsonData = null;
		JSONArray jsonArr = new JSONArray();
		
		System.out.println(limit_send_mail);
		
		GetDoctorDao vaEmail = new GetDoctorDao();
		try {
			if(report.equals("01")){
				jsonData = vaEmail.getDoctorTax406Datatable(hospitalCode, yyyy, term,limit_send_mail);
			}else if(report.equals("02")){
				jsonArr =  vaEmail.getDoctorSentSelfEmail(hospitalCode, doctorCode);
				System.out.println(jsonArr);
			}else{
				jsonData = vaEmail.getDoctorPaymentDatatable(hospitalCode, yyyy, mm,limit_send_mail);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(report.equals("02")) {
			out.println(jsonArr);
		}else {
			out.println(jsonData);
		}
		
	}

}
