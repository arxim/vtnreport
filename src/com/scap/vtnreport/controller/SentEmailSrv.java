package com.scap.vtnreport.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scap.vtnreport.dao.GetEmailDao;
import com.scap.vtnreport.service.JasperBuilderService;
import com.scap.vtnreport.service.SentEmailService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import com.scap.vtnreport.dao.GetEmailDao;
/**
 * Servlet implementation class SentEmailSrv
 */
@WebServlet("/SentEmailSrv")
public class SentEmailSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SentEmailSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		SentEmailService sentEmail = new SentEmailService();
		String message = "";

		
		String hospitalCode = request.getParameter("hospitalCode");
		String doctorCode =  request.getParameter("doctorCode");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		
		
		
		GetEmailDao vaEmail = new GetEmailDao();
		ArrayList<HashMap<String, String>> arrData = vaEmail.getEmail(hospitalCode, doctorCode, yyyy, mm);
		String email = arrData.get(0).get("EMAIL");
		Map<String, Object> params = new HashMap<>();
		params.put("hospital_code",arrData.get(0).get("HOSPITAL_CODE"));
		params.put("from_doctor", arrData.get(0).get("DOCTOR_CODE"));
		params.put("to_doctor",arrData.get(0).get("DOCTOR_CODE"));
		params.put("month",arrData.get(0).get("MM"));
		params.put("year",arrData.get(0).get("YYYY"));
		params.put("doctor_category","%%");
		params.put("doctor_department","%%");
		params.put("expense_sign", "%%");
		params.put("expense_account_code","%%");
		params.put("expense_code", "%%");

		try {
			InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/ExpenseDetail.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//			voJasperBuilder.jasperBuilder(jasperStream, jasperReport, response, params, "application/pdf","InterfaceDfTransaction");
			bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","InterfaceDfTransaction");
			message  = sentEmail.Sentmail(bos, email);
			System.out.println(message);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/mail/sent_mail_payment.jsp");
//		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
