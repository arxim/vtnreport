package com.scap.vtnreport.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scap.vtnreport.dao.GetDoctorDao;
import com.scap.vtnreport.dao.StatusSendMail;
import com.scap.vtnreport.service.PrepareFileToSendMailService;
import com.scap.vtnreport.service.ReportPDFBuilderService;
import com.scap.vtnreport.service.SentEmailNewService;
import com.scap.vtnreport.service.SentEmailService;
import com.scap.vtnreport.utils.JDate;
import com.scap.vtnreport.utils.ReadProperties;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class SendEmailNewSrvl
 */
@WebServlet("/SendEmailNewSrvl")
public class SendEmailNewSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailNewSrvl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		PrepareFileToSendMailService prepareFile = new PrepareFileToSendMailService();
		Map<String, Map<String, Object>> paramConditionNameReport = new HashMap<String, Map<String, Object>>();
		Map<String, Object> paramCondition ;
		ReportPDFBuilderService prepareFile = new ReportPDFBuilderService();
		//SentEmailService sentEmail = new SentEmailService();
		SentEmailNewService sentEmail = new SentEmailNewService();
		ArrayList<HashMap<String, String>> arrData = null;
		String message = "";

		String report = request.getParameter("report");
		String hospitalCode = request.getParameter("hospitalCode");
		String mm = request.getParameter("mm");
		String yyyy = request.getParameter("yyyy");
		String doctorCode = request.getParameter("doctorCode");
		String from_doctor = request.getParameter("doctorCode");
		String to_doctor = request.getParameter("doctorCode");
		String term = request.getParameter("term");
		String printDate = request.getParameter("printDate");
		int month = Integer.parseInt(mm);
		int year = Integer.parseInt(yyyy);
		String to_date = JDate.getLastDayOfMonth(year, month);
		String email = "";
		String set_reset = request.getParameter("set_reset");
		
		// Get SubReport RealPath
		ServletContext servletContext = request.getSession().getServletContext();
		String relativeWebPath = "/WEB-INF/JasperReport/";
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
		
		//Get Property Mail
		ReadProperties prop = new ReadProperties();
		Map<String, String>  propEmailData = prop.getPropertiesData("servermail.properties", "sender_emails");
		Map<String, String>  propPassWordData = prop.getPropertiesData("servermail.properties", "sender_pwds");
		
		switch (report) {
		// Tax Report
		case "01":
			ByteArrayOutputStream bos = null;
			try {
				
				paramCondition = new HashMap<String, Object>();
				paramCondition.put("hospital_code", hospitalCode);
				paramCondition.put("doctor_code", from_doctor);
				paramCondition.put("term", term);
				paramCondition.put("mm", term);
				paramCondition.put("yyyy", yyyy);
				paramCondition.put("signature", absoluteDiskPath);
				paramCondition.put("print_date", printDate);
				paramConditionNameReport.put(absoluteDiskPath+"TaxLetter406.jasper", paramCondition);
				GetDoctorDao vaEmail = new GetDoctorDao();
				arrData = vaEmail.getDoctorSendEmailTax406(hospitalCode,doctorCode, yyyy, term);
				email = arrData.get(0).get("EMAIL").trim();
				
				System.out.println("Timing Get Doctor Sent Mail ==> "+JDate.getTime());
				
				if(!email.equals("0")){
					bos = prepareFile.generateReport(paramConditionNameReport,arrData.get(0).get("PASS_ENCRYPT"), true);
							//PrepareTaxLetter406(arrData,jasperReport,jasperStream,response,term,absoluteDiskPath,printDate);
					
					System.out.println("Timing Create Report ==> "+JDate.getTime());
					
					message  = sentEmail.SendSingleMailPdfFile(bos, email, from_doctor, report,propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
							//SendMailSingleFile(bos, email,doctorCode);
					
					System.out.println("Timing Create Report ==> "+JDate.getTime());
					
					if(message.equals("PASS")){
						StatusSendMail.SendMailTax406Success(hospitalCode, doctorCode,term,yyyy);
					}
					
					
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
			
		// All Report Of Payment Merge PDF
		case "03":
			ByteArrayOutputStream bosMergePdf = null;
			try {
				
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

				GetDoctorDao vaEmail = new GetDoctorDao();
				arrData = vaEmail.getDoctorSendEmailPayment(hospitalCode,doctorCode, yyyy, mm);
				email = arrData.get(0).get("EMAIL").trim();
				
				System.out.println("Timing Get Doctor Sent Mail ==> "+JDate.getTime());
				
				if(!email.equals("0")){
					bosMergePdf = prepareFile.generateReport(paramConditionNameReport, arrData.get(0).get("PASS_ENCRYPT"), true);
					
					System.out.println("Timing Create Report ==> "+JDate.getTime());
					
					message  = sentEmail.SendSingleMailPdfFile(bosMergePdf, email, from_doctor, report,propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
					
					System.out.println("Timing Create Report ==> "+JDate.getTime());
					message.equals("PASS");
					if(message.equals("PASS")){
						StatusSendMail.SendMailPaymentSuccess(hospitalCode, doctorCode, mm, yyyy);
					}
					
				}else{
					message = "FAIL";
				}
	
			} catch (Exception e) {
				message = "FAIL";
				e.printStackTrace();
			}
			finally {
				 if (bosMergePdf != null)
					 bosMergePdf.close();
				}
			break;

		// All Report Of Payment Merge PDF with LoopSend
		case "04":	
			ByteArrayOutputStream bosMergePdfs = null;
			try {
				
				if(set_reset.equals("set")) {
				sentEmail.SetScheduleSendMail(printDate, hospitalCode, yyyy, mm, absoluteDiskPath, report,0);
				}
				else if (set_reset.equals("reset")) {
				sentEmail.stopService();	
				}
				
				/*ReadProperties prop4 = new ReadProperties();
				Map<String, String>  propEmailData4 = prop4.getPropertiesData("servermail.properties", "sender_emails");
				Map<String, String>  propPassWordData4 = prop4.getPropertiesData("servermail.properties", "sender_pwds");
				Map<String, String>  props = prop.getDataReadPropertiesFile("servermail.properties");
				int limit_send = Integer.parseInt(props.get("limit_send"));
				int j=0 ,i=0;
				String list_report ="";
				System.out.println(propEmailData4);
				for(i=0;i< propEmailData4.size();i++) {
					System.out.println(i+"   "+propEmailData4.get("sender_emails."+i));
					System.out.println(i+"   "+propPassWordData4.get("sender_pwds."+i));
					
					GetDoctorDao vaEmail = new GetDoctorDao();
					arrData = vaEmail.getAllDoctorSendEmailPayment(hospitalCode, yyyy, mm);
					for(j=0; j< limit_send; j++) {
						
						System.out.println(j+"  "+arrData.get(j).get("DOCTOR_CODE")+"    password="+arrData.get(j).get("PASS_ENCRYPT").trim());
				
						if(!arrData.get(j).get("VOUCHER").equals("0.00")) {
							paramCondition = new HashMap<String, Object>();
							paramCondition.put("from_doctor", arrData.get(j).get("DOCTOR_CODE"));
							paramCondition.put("to_doctor", arrData.get(j).get("DOCTOR_CODE"));
							paramCondition.put("month", mm);
							paramCondition.put("year", yyyy);
							paramCondition.put("from_date", "00000000");
							paramCondition.put("to_date", to_date);
							paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
							paramConditionNameReport.put(absoluteDiskPath + "PaymentVoucher.jasper", paramCondition);
							list_report="Y";
						}
						if(!arrData.get(j).get("REVENUE_DETAIL").equals("0.00")) {
							paramCondition = new HashMap<String, Object>();
							paramCondition.put("hospital_code", hospitalCode);
							paramCondition.put("from_doctor", arrData.get(j).get("DOCTOR_CODE"));
							paramCondition.put("to_doctor", arrData.get(j).get("DOCTOR_CODE"));
							paramCondition.put("month", mm);
							paramCondition.put("year", yyyy);
							paramCondition.put("doctor_category", "%%");
							paramCondition.put("doctor_department", "%%");
							paramCondition.put("order_item", "%%");
							paramCondition.put("order_item_category", "%%");
							paramConditionNameReport.put(absoluteDiskPath + "SummaryRevenueByDetail.jasper", paramCondition);
							list_report="Y";
						}
						if(!arrData.get(j).get("UNPAID").equals("0.00")) {
							paramCondition = new HashMap<String, Object>();
							paramCondition.put("from_date", "00000000");
							paramCondition.put("to_date", to_date);
							paramCondition.put("doctor", arrData.get(j).get("DOCTOR_CODE"));
							paramCondition.put("hospital_code", hospitalCode);
							paramCondition.put("as_of_date", "%%");
							paramCondition.put("department_code", "%%");
							paramCondition.put("payor_code", "%%");
							paramConditionNameReport.put(absoluteDiskPath + "SummaryDFUnpaidByDetailAsOfDate.jasper", paramCondition);
							list_report="Y";

						}
						if(!arrData.get(j).get("EXPENSE").equals("0.00")) {
							paramCondition = new HashMap<String, Object>();
							paramCondition.put("hospital_code", hospitalCode);
							paramCondition.put("from_doctor", arrData.get(j).get("DOCTOR_CODE"));
							paramCondition.put("to_doctor", arrData.get(j).get("DOCTOR_CODE"));
							paramCondition.put("month", mm);
							paramCondition.put("year", yyyy);
							paramCondition.put("doctor_category", "%%");
							paramCondition.put("doctor_department", "%%");
							paramCondition.put("order_item", "%%");
							paramCondition.put("order_item_category", "%%");
							paramConditionNameReport.put(absoluteDiskPath + "ExpenseDetail.jasper", paramCondition);
							list_report="Y";
						}
						
						if(!arrData.get(j).get("EMAIL").trim().equals("0")) {
							if(!list_report.equals("")) {
								bosMergePdfs = prepareFile.generateReport(paramConditionNameReport, arrData.get(j).get("PASS_ENCRYPT").trim(), true);
								message  = sentEmail.SendSingleMailPdfFile(bosMergePdfs, arrData.get(j).get("EMAIL").trim(), arrData.get(j).get("DOCTOR_CODE"), report,propEmailData4.get("sender_emails."+i),propPassWordData4.get("sender_pwds."+i));
								
							}
							
						}else {message = "FAIL";}
						
						if(message.equals("PASS") && list_report.equals("Y")){
							StatusSendMail.SendMailPaymentSuccess(hospitalCode, arrData.get(j).get("DOCTOR_CODE"),mm,yyyy);
						}
						list_report="";
					}
					if(i==propEmailData.size()) {break;}else {}
				}
				*/
				
			} catch (Exception e) {
				message = "FAIL";
				e.printStackTrace();
			}
			finally {
				 if (bosMergePdfs != null)
					 bosMergePdfs.close();
				}
			break;
			
		default:
			break;
			
			
		}
		out.print(message);
	}
	
	
	

}
