package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scap.vtnreport.service.ReportPDFBuilderService;
import com.scap.vtnreport.utils.JDate;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class ReportPDFBuilderSrvl
 */
@WebServlet("/ReportPDFBuilderSrvl")
public class ReportPDFBuilderSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportPDFBuilderSrvl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Map<String, Map<String, Object>> paramConditionNameReport = new HashMap<String, Map<String, Object>>();
		Map<String, Object> paramCondition ;
		ReportPDFBuilderService genReport = new ReportPDFBuilderService();
		
		String from_doctor = request.getParameter("hidDoctorCode");
		String to_doctor = request.getParameter("hidDoctorCode");
		String report = request.getParameter("hidReport");
		String hospitalCode = request.getParameter("hidHospitalCode");
		String mm = request.getParameter("hidMM");
		String yyyy = request.getParameter("hidYYYY");
		String term = request.getParameter("hidTerm");
		String printDate = request.getParameter("hidPrintDate");
		String to_date="";
		
		int role = ((BigDecimal) session.getAttribute("role")).intValue();
		String user = (String) session.getAttribute("userid");
		
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
		
		// Role User when doctorCode is Empty
		if ((role == 4 || role == 1) && from_doctor.isEmpty()) {

			if (report.equals("tax")) {
				from_doctor = "%%";
			} else {
				from_doctor = "00000";
			}
			to_doctor = "99999";
		} else {
			from_doctor = to_doctor;
		}
				
		// Role Permission to print report 
		boolean isPrint = role == 4 || role == 1 ? true : false;
		
		// Get SubReport RealPath
		ServletContext servletContext = request.getSession().getServletContext();
		String relativeWebPath = "/WEB-INF/JasperReport/";
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
		
		System.out.println(report);
		
		switch (report) {
		// PaymentVoucher
		case "01":
			paramCondition = new HashMap<String, Object>();
			paramCondition.put("from_doctor", from_doctor);
			paramCondition.put("to_doctor", to_doctor);
			paramCondition.put("month", mm);
			paramCondition.put("year", yyyy);
			paramCondition.put("from_date", "00000000");
			paramCondition.put("to_date", to_date);
			paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
			paramConditionNameReport.put(absoluteDiskPath+"PaymentVoucher.jasper", paramCondition);
			
			genReport.viewPDFReport(paramConditionNameReport, "", isPrint , response);
			break;
			
		// SummaryRevenueByDetail
		case "02":
			
			paramCondition = new HashMap<String, Object>();
			paramCondition.put("hospital_code", hospitalCode);
			paramCondition.put("from_doctor", from_doctor);
			paramCondition.put("to_doctor", to_doctor);
			paramCondition.put("month", mm);
			paramCondition.put("year", yyyy);
			paramCondition.put("doctor_category", "%%");
			paramCondition.put("doctor_department", "%%");
			paramCondition.put("order_item", "%%");
			paramCondition.put("order_item_category", "%%");
			paramConditionNameReport.put(absoluteDiskPath+"SummaryRevenueByDetail.jasper", paramCondition);
			
			genReport.viewPDFReport(paramConditionNameReport, "", isPrint , response);
			break;
			
		// SummaryDFUnpaidByDetailAsOfDate
		case "03":
			paramCondition = new HashMap<String, Object>();
			paramCondition.put("from_date", "00000000");
			paramCondition.put("to_date", to_date);
			paramCondition.put("doctor", to_doctor);
			paramCondition.put("hospital_code", hospitalCode);
			paramCondition.put("as_of_date", "%%");
			paramCondition.put("department_code", "%%");
			paramCondition.put("payor_code", "%%");
			paramConditionNameReport.put(absoluteDiskPath+"SummaryDFUnpaidByDetailAsOfDate.jasper", paramCondition);
			
			genReport.viewPDFReport(paramConditionNameReport, "", isPrint , response);
			break;
			
		// ExpenseDetail.jasper
		case "04":
			paramCondition = new HashMap<String, Object>();
			paramCondition.put("hospital_code", hospitalCode);
			paramCondition.put("from_doctor", from_doctor);
			paramCondition.put("to_doctor", to_doctor);
			paramCondition.put("month", mm);
			paramCondition.put("year", yyyy);
			paramCondition.put("doctor_category", "%%");
			paramCondition.put("doctor_department", "%%");
			paramCondition.put("order_item", "%%");
			paramCondition.put("order_item_category", "%%");
			paramConditionNameReport.put(absoluteDiskPath+"ExpenseDetail.jasper", paramCondition);
			
			genReport.viewPDFReport(paramConditionNameReport, "", isPrint , response);
			break;				
			
		// Merge PDF 4 file
		case "05":
			paramCondition = new HashMap<String, Object>();
			paramCondition.put("from_doctor", from_doctor);
			paramCondition.put("to_doctor", to_doctor);
			paramCondition.put("month", mm);
			paramCondition.put("year", yyyy);
			paramCondition.put("from_date", "00000000");
			paramCondition.put("to_date", to_date);
			paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
			paramConditionNameReport.put(absoluteDiskPath + "PaymentVoucher.jasper", paramCondition);

			paramCondition = new HashMap<String, Object>();
			paramCondition.put("hospital_code", hospitalCode);
			paramCondition.put("from_doctor", from_doctor);
			paramCondition.put("to_doctor", to_doctor);
			paramCondition.put("month", mm);
			paramCondition.put("year", yyyy);
			paramCondition.put("doctor_category", "%%");
			paramCondition.put("doctor_department", "%%");
			paramCondition.put("order_item", "%%");
			paramCondition.put("order_item_category", "%%");
			paramConditionNameReport.put(absoluteDiskPath + "SummaryRevenueByDetail.jasper", paramCondition);

			paramCondition = new HashMap<String, Object>();
			paramCondition.put("from_date", "00000000");
			paramCondition.put("to_date", to_date);
			paramCondition.put("doctor", to_doctor);
			paramCondition.put("hospital_code", hospitalCode);
			paramCondition.put("as_of_date", "%%");
			paramCondition.put("department_code", "%%");
			paramCondition.put("payor_code", "%%");
			paramConditionNameReport.put(absoluteDiskPath + "SummaryDFUnpaidByDetailAsOfDate.jasper", paramCondition);

			paramCondition = new HashMap<String, Object>();
			paramCondition.put("hospital_code", hospitalCode);
			paramCondition.put("from_doctor", from_doctor);
			paramCondition.put("to_doctor", to_doctor);
			paramCondition.put("month", mm);
			paramCondition.put("year", yyyy);
			paramCondition.put("doctor_category", "%%");
			paramCondition.put("doctor_department", "%%");
			paramCondition.put("order_item", "%%");
			paramCondition.put("order_item_category", "%%");
			paramConditionNameReport.put(absoluteDiskPath + "ExpenseDetail.jasper", paramCondition);

			genReport.viewPDFReport(paramConditionNameReport, "", isPrint , response);
			break;	
		
		// TaxLetter406.jasper
		case "tax":
			paramCondition = new HashMap<String, Object>();
			paramCondition.put("hospital_code", hospitalCode);
			paramCondition.put("doctor_code", from_doctor);
			paramCondition.put("term", term);
			paramCondition.put("mm", term);
			paramCondition.put("yyyy", yyyy);
			paramCondition.put("signature", absoluteDiskPath);
			paramCondition.put("print_date", printDate);
			paramConditionNameReport.put(absoluteDiskPath+"TaxLetter406.jasper", paramCondition);
			
			genReport.viewPDFReport(paramConditionNameReport, "", isPrint , response);
			
			break;

		default:
			break;
			
	}
		
		
		
		
		
		
		
	}

}
