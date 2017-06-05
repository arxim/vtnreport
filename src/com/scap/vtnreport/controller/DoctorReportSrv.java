package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.InputStream;
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
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String value = request.getParameter("");

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
			voJasperBuilder.jasperBuilder(jasperStream, jasperReport, response, params, "application/pdf","InterfaceDfTransaction");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/pages/report/doctor_report.jsp");

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
