package com.scap.vtnreport.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scap.vtnreport.service.JasperBuilderService;
import com.scap.vtnreport.service.SentEmailService;
import com.scap.vtnreport.utils.JDate;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class DoctorReportSrv
 */
@WebServlet("/DoctorReportSrv")
public class DoctorReportSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoctorReportSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Map<String, Object> params = new HashMap<>();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		
		int role = ((BigDecimal) session.getAttribute("role")).intValue();
		String user = (String) session.getAttribute("userid");
		
		
		
		String from_doctor = request.getParameter("hidDoctorCode");
		String to_doctor = request.getParameter("hidDoctorCode");
		String report = request.getParameter("hidReport");
		String hospitalCode = request.getParameter("hidHospitalCode");
		String mm = request.getParameter("hidMM");
		String yyyy = request.getParameter("hidYYYY");
		String term = request.getParameter("hidTerm");
		String printDate = request.getParameter("hidPrintDate");
		String to_date="";
		
		if(role != 4 && role != 1){
			from_doctor = user;
			to_doctor = user;
		}
		
		// Get Last Day of Month
		if(!mm.isEmpty()){
			int month = Integer.parseInt(mm);
			int year = Integer.parseInt(yyyy);
			to_date = JDate.getLastDayOfMonth(year, month);
		}
		
		
		// Role Permission to print report 
		String permission = role == 4 || role == 1 ? "Y" : "N";

		// Role User when doctorCode is Empty
		if((role == 4 || role == 1) && from_doctor.isEmpty()){
			
			if (report.equals("tax")) {
				from_doctor = "%%";
			} else {
				from_doctor = "00000";
			}
			to_doctor = "99999";
		}else{
			from_doctor = to_doctor;
		}
		
		// Get SubReport RealPath
		ServletContext servletContext = request.getSession().getServletContext();
		String relativeWebPath = "/WEB-INF/JasperReport/";
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);

		System.out.println(role);
		
		switch (report) {
		
		// PaymentVoucher
		case "01":
			
			params.put("from_doctor", from_doctor);
			params.put("to_doctor",to_doctor);
			params.put("month",mm);
			params.put("year",yyyy);
			params.put("from_date","00000000");
			params.put("to_date",to_date);
			params.put("SUBREPORT_DIR",absoluteDiskPath);
			
			try {
				InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/PaymentVoucher.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				voJasperBuilder.jasperBuilder(jasperStream,jasperReport, response,params, "application/pdf","PaymentVoucher",permission);
			} catch (JRException | SQLException e) {
				e.printStackTrace();
			}
			
			
			break;
			
		// SummaryRevenueByDetail
		case "02":
			
			params.put("hospital_code",hospitalCode);
			params.put("from_doctor", from_doctor);
			params.put("to_doctor",to_doctor);
			params.put("month",mm);
			params.put("year",yyyy);
			params.put("doctor_category","%%");
			params.put("doctor_department","%%");
			params.put("order_item", "%%");
			params.put("order_item_category","%%");
			
			try {
				InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/SummaryRevenueByDetail.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				voJasperBuilder.jasperBuilder(jasperStream,jasperReport, response,params, "application/pdf","SummaryRevenueByDetail",permission);
			} catch (JRException | SQLException e) {
				e.printStackTrace();
			}

			break;
			
		// SummaryDFUnpaidByDetailAsOfDate
		case "03":
			
			params.put("from_date","00000000");
			params.put("to_date",to_date);
			params.put("doctor",to_doctor);
			params.put("hospital_code",hospitalCode);
			params.put("as_of_date","%%");
			params.put("department_code","%%");
			params.put("payor_code","%%");
			
			try {
				InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/SummaryDFUnpaidByDetailAsOfDate.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				voJasperBuilder.jasperBuilder(jasperStream,jasperReport, response,params, "application/pdf","SummaryDFUnpaidByDetailAsOfDate",permission);
			} catch (JRException | SQLException e) {
				e.printStackTrace();
			}
			
			break;
			
		// ExpenseDetail.jasper
		case "04":
			
			params.put("hospital_code",hospitalCode);
			params.put("from_doctor", from_doctor);
			params.put("to_doctor",to_doctor);
			params.put("month",mm);
			params.put("year",yyyy);
			params.put("doctor_category","%%");
			params.put("doctor_department","%%");
			params.put("order_item", "%%");
			params.put("order_item_category","%%");

	        try {
				InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/ExpenseDetail.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				voJasperBuilder.jasperBuilder(jasperStream,jasperReport, response,params, "application/pdf","ExpenseDetail",permission);
			} catch (JRException | SQLException e) {
				e.printStackTrace();
			}

			break;
			
		// TaxLetter406.jasper
		case "tax" :
			
			params.put("hospital_code",hospitalCode);
			params.put("doctor_code",from_doctor);
			params.put("term",term);
			params.put("mm", term);
			params.put("yyyy",yyyy);
			params.put("signature", absoluteDiskPath);
			params.put("print_date", printDate);

			try {
				InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/TaxLetter406.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				voJasperBuilder.jasperBuilder(jasperStream,jasperReport, response,params, "application/pdf","TaxLetter406","Y");
			} catch (JRException | SQLException e) {
				e.printStackTrace();
			}
			
			break;
			
		default:
			break;
		}
	}

}
