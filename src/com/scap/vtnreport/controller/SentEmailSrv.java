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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scap.vtnreport.dao.GetDoctorDao;
import com.scap.vtnreport.dao.StatusSendMail;
import com.scap.vtnreport.service.JasperBuilderService;
import com.scap.vtnreport.service.PrepareFileToSendMailService;
import com.scap.vtnreport.service.SentEmailService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import com.scap.vtnreport.dao.StatusSendMail;
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		PrepareFileToSendMailService prepareFile = new PrepareFileToSendMailService();
		SentEmailService sentEmail = new SentEmailService();
		ArrayList<HashMap<String, String>> arrData = null;
		GetDoctorDao vaEmail = new GetDoctorDao();
		String message = "";

		String report = request.getParameter("report");
		String hospitalCode = request.getParameter("hospitalCode");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String doctorCode = request.getParameter("doctorCode");
		String term = request.getParameter("term");
		String email = "";
		
		switch (report) {
		// Tax Report
		case "01":
			ByteArrayOutputStream bos = null;
			try {
				InputStream jasperStream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/TaxLetter406.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				
				// Get SubReport RealPath
				ServletContext servletContext = request.getSession().getServletContext();
				String relativeWebPath = "/WEB-INF/JasperReport/";
				String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
				
				arrData = vaEmail.getDoctorSendEmailTax406(hospitalCode,doctorCode, yyyy, term);
				email = arrData.get(0).get("EMAIL").trim();
				
				if(!email.equals("0")){
					bos = prepareFile.PrepareTaxLetter406(arrData,jasperReport,jasperStream,response,term,absoluteDiskPath);
					message  = sentEmail.SendMailSingleFile(bos, email);
					StatusSendMail.SendMailTax406Success(hospitalCode, doctorCode,mm,yyyy);
				}else{
					message = "FAIL";
				}
	
			} catch (Exception e) {
				message = "FAIL";
				e.printStackTrace();
			}
			finally {
				 if (bos != null)
					 bos.close();
				}
			break;
			
		// All Report Of Payment
		case "02":
			ByteArrayOutputStream bos1 = null,bos2 = null,bos3 = null,bos4 = null;
			try {
				InputStream jasperStream1 = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/PaymentVoucher.jasper");
				InputStream jasperStream2 = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/SummaryRevenueByDetail.jasper");
				InputStream jasperStream3 = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/ExpenseDetail.jasper");
				InputStream jasperStream4 = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/JasperReport/DFHold.jasper");
				
				// Get SubReport RealPath
				ServletContext servletContext = request.getSession().getServletContext();
				String relativeWebPath = "/WEB-INF/JasperReport/";
				String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
				
				JasperReport jasperReport1 = (JasperReport) JRLoader.loadObject(jasperStream1);
				JasperReport jasperReport2 = (JasperReport) JRLoader.loadObject(jasperStream2);
				JasperReport jasperReport3= (JasperReport) JRLoader.loadObject(jasperStream3);
				JasperReport jasperReport4 = (JasperReport) JRLoader.loadObject(jasperStream4);
				
				arrData = vaEmail.getDoctorSendEmailPayment(hospitalCode,doctorCode, yyyy, mm);
				email = arrData.get(0).get("EMAIL").trim();
				
				if(!email.equals("0")){
					
					bos1 = prepareFile.PreparePaymentVoucher(arrData,jasperReport1,jasperStream1,response,absoluteDiskPath);
					bos2 = prepareFile.PrepareSummaryRevenueByDetail(arrData,jasperReport2,jasperStream2,response);
					bos3 = prepareFile.PrepareExpenseDetail(arrData,jasperReport3,jasperStream3,response);
					bos4 = prepareFile.PrepareDFHold(arrData,jasperReport4,jasperStream4,response);
					
					message  = sentEmail.SendMailMultiFile(bos1,bos2,bos3,bos4, email);
					StatusSendMail.SendMailPaymentSuccess(hospitalCode, doctorCode,mm,yyyy);
				}else{
					message = "FAIL";
				}
	
			} catch (Exception e) {
				message = "FAIL";
				e.printStackTrace();
			}
			 finally {
				 if (bos1 != null)
					 bos1.close();
				 if(bos2 !=null)
					 bos2.close();
				 if(bos3 != null)
					 bos3.close();
				 if(bos4 != null)
					 bos4.close();
				}
			
			break;

		default:
			break;
		}
		out.print(message);
	}
}

