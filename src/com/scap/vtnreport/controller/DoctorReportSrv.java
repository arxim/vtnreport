package com.scap.vtnreport.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scap.vtnreport.service.JasperBuilderService;
import com.scap.vtnreport.service.SentEmailService;

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
		
		String report = request.getParameter("report");
		String hospitalCode = request.getParameter("hospitalCode");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String doctorCode = request.getParameter("doctorCode");
		String term = request.getParameter("term");
		
		switch (report) {
		case "01":
			JasperBuilderService voJasperBuilder = new JasperBuilderService();
			Map<String, Object> params = new HashMap<>();
			params.put("hospital_code",hospitalCode);
			params.put("from_doctor", doctorCode);
			params.put("to_doctor",doctorCode);
			params.put("month",mm);
			params.put("year",yyyy);
			params.put("doctor_category","%%");
			params.put("doctor_department","%%");
			params.put("order_item", "%%");
			params.put("order_item_category","%%");

	        try {
	        	//jasperStream = this.getClass().getResourceAsStream(jasperFile);
				InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/ExpenseDetail.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				// Generate Jasper Report
				voJasperBuilder.jasperBuilder(jasperStream,jasperReport, response,params, "application/pdf","name");
//				out.print(voJasperBuilder);
			} catch (JRException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case "02":
			
			break;
		case "03":
			
			break;
		case "04":
			
			break;
			
		default:
			break;
		}
		
/*		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		SentEmailService sentEmail = new SentEmailService();
		String message = "";

		// add params to hash map
		Map<String, Object> params = new HashMap<>();
		params.put("hospital_code","VTN01");
		params.put("from_doctor", "111111");
		params.put("to_doctor","999999");
		params.put("month","03");
		params.put("year","2010");
		params.put("doctor_category","%%");
		params.put("doctor_department","%%");
		params.put("expense_sign", "%%");
		params.put("expense_account_code","%%");
		params.put("expense_code", "%%");

		try {
			InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/ExpenseDetail.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//			voJasperBuilder.jasperBuilder(jasperStream, jasperReport, response, params, "application/pdf","InterfaceDfTransaction");
			bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","InterfaceDfTransaction","");
			message  = sentEmail.SendMailSingleFile(bos, "sompong21153001@hotmail.co.th");
			System.out.println(message);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

//		RequestDispatcher dispatcher = this.getServletContext()
//				.getRequestDispatcher("/WEB-INF/pages/report/doctor_report.jsp");

		//dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
