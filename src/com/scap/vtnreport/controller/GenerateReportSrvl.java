package com.scap.vtnreport.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scap.vtnreport.dao.GenerateReportDao;
import com.scap.vtnreport.dao.GetDoctorDao;
import com.scap.vtnreport.service.ReportPDFBuilderService;
import com.scap.vtnreport.service.SentEmailNewService;
import com.scap.vtnreport.utils.ConvertToString;
import com.scap.vtnreport.utils.ConvertToStringThai;
import com.scap.vtnreport.utils.JDate;
import com.scap.vtnreport.utils.ReadProperties;

/**
 * Servlet implementation class GenerateReportSrvl
 */
@WebServlet("/GenerateReportSrvl")
public class GenerateReportSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateReportSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		PrepareFileToSendMailService prepareFile = new PrepareFileToSendMailService();
//		Map<String, Map<String, Object>> paramConditionNameReport = new HashMap<String, Map<String, Object>>();
//		Map<String, Object> paramCondition ;
//		ReportPDFBuilderService prepareFile = new ReportPDFBuilderService();
//		SentEmailService sentEmail = new SentEmailService();
		SentEmailNewService sentEmail = new SentEmailNewService();
		ArrayList<HashMap<String, String>> arrData = null;
		String message = "";
//		HttpSession session=request.getSession();
		Map<String, Map<String, Object>> paramConditionNameReport = new HashMap<String, Map<String, Object>>();
		Map<String, Object> paramCondition ;
		ByteArrayOutputStream bos = null;
		ReportPDFBuilderService genReport = new ReportPDFBuilderService();
		GenerateReportDao vaDetail = new GenerateReportDao();
		String title ="";
		String header = "";
		String columnheader = "";
		String form = "";
		String form2 = "";
		String form3 = "";
		String columnfooter = "";
		String footer = "";
		String getrun = "";
		String numberAsString ="";
		String getavg = "";
		String avg_format = "";
		String datetimeEng = "";
		int set_avg_format = 0;
		String thaitext =  "";
		String engtext = "";
		DecimalFormat formatter = new DecimalFormat("#,###");
		SimpleDateFormat DateFormat = new SimpleDateFormat("MMMMM dd,yyyy");
		SimpleDateFormat datedb = new SimpleDateFormat("yyyyMMDD");
		Date date = new Date();  
		String datetime = DateFormat.format(date);
		
		String doctorCode = request.getParameter("hidDoctorCode");
		String reportCode = request.getParameter("hidReport");
		String startMM = request.getParameter("hidStartMM");
		String startYYYY = request.getParameter("hidStartYYYY");
		String endMM = request.getParameter("hidEndMM");
		String endYYYY = request.getParameter("hidEndYYYY");
		String MeetingName = request.getParameter("hidMeetingName");
		String MeetingDate = request.getParameter("hidMeetingDate");
		String Location = request.getParameter("hidtxtCounty").split(":")[0];

//		String reportCode = request.getParameter("reportCode");
//		String startMM = request.getParameter("startMM");
//		String startYYYY = request.getParameter("startYYYY");
//		String endMM = request.getParameter("endMM");
//		String endYYYY = request.getParameter("endYYYY");
//		String doctorCode = request.getParameter("doctorCode");
//		String meetingName = request.getParameter("meetingName");
//		String location = request.getParameter("location");
//		String meetingDate = request.getParameter("meetingDate");
		String type = request.getParameter("hidtype");
		
		String change = startMM;
		if(Integer.parseInt(startMM) > Integer.parseInt(endMM)) {
			startMM = endMM;
			endMM = change;
		}
		
		// Get SubReport RealPath
		ServletContext servletContext = request.getSession().getServletContext();
		String relativeWebPath = "/WEB-INF/JasperReport/";
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
		
		//Get Property Mail
		ReadProperties prop = new ReadProperties();
		Map<String, String>  propEmailData = prop.getPropertiesData("servermail.properties", "sender_emails");
		Map<String, String>  propPassWordData = prop.getPropertiesData("servermail.properties", "sender_pwds");
		
		//Get Property ReportDetail
		Map<String, String>  propTitle = prop.getPropertiesData("reportdetail.properties", "title");
		Map<String, String>  propHeader = prop.getPropertiesData("reportdetail.properties", "header");
		Map<String, String>  propColumnHeader = prop.getPropertiesData("reportdetail.properties", "columnheader");
		Map<String, String>  propForm = prop.getPropertiesData("reportdetail.properties", "form_eng");
		Map<String, String>  propFooter = prop.getPropertiesData("reportdetail.properties", "footer");
		
		
		switch(reportCode) {
		case "01":
//				ByteArrayOutputStream bos = null;

			try {
				arrData = vaDetail.getGenerateReport(doctorCode, startYYYY, startMM, endMM, endYYYY);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				getrun = arrData.get(0).get("RUNNING_NUMBER");
				numberAsString = String.format ("%03d", Integer.parseInt(getrun));
//				String thaitext =  new ConvertToStringThai().getText(arrData.get(0).get("AVG_AMT"));
//				String getavg = arrData.get(0).get("AVG_AMT");
////				DecimalFormat df = new DecimalFormat("0.00##");
////				String result = df.format(getavg);
//				String avg_format = String.format ("%.0f", Float.parseFloat(getavg));
				
//				
				getavg = arrData.get(0).get("AVG_AMT");
				avg_format = String.format ("%.0f", Float.parseFloat(getavg));
				set_avg_format = ((int)(Math.round( Integer.parseInt(avg_format) / 10.0) * 10));
				thaitext =  new ConvertToStringThai().getText(set_avg_format);
				
//				System.out.println(test1+numberAsString+thaitext+JDate.getDateDD_MM_YYY());
				paramCondition = new HashMap<String, Object>();
				paramCondition.put("current_date", JDate.getDateDD_MM_YYY());
				paramCondition.put("Running_Number", numberAsString);
				paramCondition.put("AVG_Amount", formatter.format(Double.parseDouble(avg_format)));
				paramCondition.put("thai_form", thaitext);
				paramCondition.put("doctor_code", doctorCode);
				paramCondition.put("startMM", startMM);
				paramCondition.put("startYYYY", startYYYY);
				paramCondition.put("endMM", endMM);
				paramCondition.put("endYYYY", endYYYY);
//				paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
				paramConditionNameReport.put(absoluteDiskPath+"IncomeCerificate.jasper", paramCondition);
				if(type.equals("1")) {
				genReport.viewPDFReport(paramConditionNameReport, "", true , response);
				}
				else {
					bos = genReport.generateReport(paramConditionNameReport,"", true);	
					message  = sentEmail.SendSingleMailPdfFile(bos, propEmailData.get("sender_emails.0"), doctorCode, "03",propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
				}
				
			break;
			
		case "02":
//			ByteArrayOutputStream bos = null;

			try {
				arrData = vaDetail.getGenerateReport(doctorCode, startYYYY, startMM, endMM, endYYYY);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				getrun = arrData.get(0).get("RUNNING_NUMBER");
				numberAsString = String.format ("%03d", Integer.parseInt(getrun));
//				String thaitext =  new ConvertToStringThai().getText(arrData.get(0).get("AVG_AMT"));
//				String getavg = arrData.get(0).get("AVG_AMT");
////				DecimalFormat df = new DecimalFormat("0.00##");
////				String result = df.format(getavg);
//				String avg_format = String.format ("%.0f", Float.parseFloat(getavg));
				
//				
				getavg = arrData.get(0).get("AVG_AMT");
				avg_format = String.format ("%.0f", Float.parseFloat(getavg));
				set_avg_format = ((int)(Math.round( Integer.parseInt(avg_format) / 10.0) * 10));
				thaitext =  new ConvertToStringThai().getText(set_avg_format);
				
//				System.out.println(test1+numberAsString+thaitext+JDate.getDateDD_MM_YYY());
				paramCondition = new HashMap<String, Object>();
				paramCondition.put("current_date", JDate.getDateDD_MM_YYY());
				paramCondition.put("Running_Number", numberAsString);
				paramCondition.put("AVG_Amount", formatter.format(Double.parseDouble(avg_format)));
				paramCondition.put("thai_form", thaitext);
				paramCondition.put("doctor_code", doctorCode);
				paramCondition.put("startMM", startMM);
				paramCondition.put("startYYYY", startYYYY);
				paramCondition.put("endMM", endMM);
				paramCondition.put("endYYYY", endYYYY);
//				paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
				paramConditionNameReport.put(absoluteDiskPath+"IncomeCerificate2.jasper", paramCondition);
				if(type.equals("1")) {
					genReport.viewPDFReport(paramConditionNameReport, "", true , response);
					}
					else {
						bos = genReport.generateReport(paramConditionNameReport,"", true);	
						message  = sentEmail.SendSingleMailPdfFile(bos, propEmailData.get("sender_emails.0"), doctorCode, "03",propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
					}
					
			break;
			
		case "03":
//			ByteArrayOutputStream bos = null;
		
	
			try {
				arrData = vaDetail.getGenerateReportEng(doctorCode, startYYYY, startMM, endMM, endYYYY);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			getrun = arrData.get(0).get("RUNNING_NUMBER");
			numberAsString = String.format ("%03d", Integer.parseInt(getrun));
		
			getavg = arrData.get(0).get("AVG_AMT");
			avg_format = String.format ("%.0f", Float.parseFloat(getavg));
			set_avg_format = ((int)(Math.round( Integer.parseInt(avg_format) / 10.0) * 10));
			engtext = ConvertToString.convert(set_avg_format);
			
//			DateFormat.format(arrData.get(0).get("FROM_DATE"))
			try {
				datetimeEng = DateFormat.format(datedb.parse(arrData.get(0).get("FROM_DATE")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			title = propTitle.get("title_1")+"\n"+propTitle.get("title_2")+"\n"+propTitle.get("title_3")+"\n"+propTitle.get("title_4")+"\n"+propTitle.get("title_5");
			header = propHeader.get("header_1")+"\n"+propHeader.get("header_2")+numberAsString+"/"+arrData.get(0).get("batch_date").substring(2, 4);
			columnheader = propColumnHeader.get("columnheader_1");
			form = arrData.get(0).get("NAME_ENG")+" "+propForm.get("form_eng_1")+datetimeEng+" "+propForm.get("form_eng_2")
				   +arrData.get(0).get("POSITION")+" "+propForm.get("form_eng_3")+arrData.get(0).get("DESCRIPTION")+" "+propForm.get("form_eng_4")+formatter.format(set_avg_format)+" ("+engtext+" Bhat Only).";
			form2 = propForm.get("form_eng_8");
//			form2 = arrData.get(0).get("NAME_ENG")+" "+propForm.get("form_eng_meet_1")+propForm.get("form_eng_meet_2");
//			form3 = propForm.get("form_eng_8");
			columnfooter = propForm.get("form_eng_9")+"\n"+propForm.get("form_eng_10");
			footer = propFooter.get("footer_1")+"\n"+propFooter.get("footer_2")+"\n"+propFooter.get("footer_3");
			
			
//			title = "zzz";
//			header = "qwe";
//			columnheader = "asxcvv";
//			form = "sfa";
//			form2 = "azz";
//			form3 = "aw";
//			columnfooter = "ss";
//			footer = "test";
			
			paramCondition = new HashMap<String, Object>();
//			paramCondition.put("current_date", JDate.getDateDD_MM_YYY());
//			paramCondition.put("Running_Number", numberAsString);
//			paramCondition.put("AVG_Amount", formatter.format(Double.parseDouble(avg_format)));
//			paramCondition.put("thai_form", thaitext);
			paramCondition.put("title", title);
			paramCondition.put("header", header);
			paramCondition.put("currenttime", datetime);
			paramCondition.put("columnheader", columnheader);
			paramCondition.put("detail", form);
			paramCondition.put("detail2", form2);
			paramCondition.put("detail3", form3);
			paramCondition.put("columnfooter", columnfooter);
			paramCondition.put("footer", footer);
			paramCondition.put("doctor_name", arrData.get(0).get("NAME_ENG"));
//			paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
			paramConditionNameReport.put(absoluteDiskPath+"IncomeCerificateEng.jasper", paramCondition);
			if(type.equals("1")) {
			genReport.viewPDFReport(paramConditionNameReport, "", true , response);
			}
			else {
				bos = genReport.generateReport(paramConditionNameReport,"", true);	
				message  = sentEmail.SendSingleMailPdfFile(bos, propEmailData.get("sender_emails.0"), doctorCode, "03",propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
			}
			
			break;
		case "04" :


			try {
				arrData = vaDetail.getGenerateReportEng(doctorCode, startYYYY, startMM, endMM, endYYYY);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			getrun = arrData.get(0).get("RUNNING_NUMBER");
			numberAsString = String.format ("%03d", Integer.parseInt(getrun));
		
			getavg = arrData.get(0).get("AVG_AMT");
			avg_format = String.format ("%.0f", Float.parseFloat(getavg));
			set_avg_format = ((int)(Math.round( Integer.parseInt(avg_format) / 10.0) * 10));
			engtext = ConvertToString.convert(set_avg_format);
			
//			DateFormat.format(arrData.get(0).get("FROM_DATE"))
			try {
				datetimeEng = DateFormat.format(datedb.parse(arrData.get(0).get("FROM_DATE")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			title = propTitle.get("title_1")+"\n"+propTitle.get("title_2")+"\n"+propTitle.get("title_3")+"\n"+propTitle.get("title_4")+"\n"+propTitle.get("title_5");
			header = propHeader.get("header_1")+"\n"+propHeader.get("header_2")+numberAsString+"/"+arrData.get(0).get("batch_date").substring(2, 4);
			columnheader = propColumnHeader.get("columnheader_1");
			form = " "+propForm.get("form_eng_1")+datetimeEng+" "+propForm.get("form_eng_2")
				   +arrData.get(0).get("POSITION")+" "+propForm.get("form_eng_3")+arrData.get(0).get("DESCRIPTION")+" "+propForm.get("form_eng_4")+formatter.format(set_avg_format)+" ("+engtext+" Bhat Only).";
//			form2 = propForm.get("form_eng_8");
			form2 = arrData.get(0).get("NAME_ENG")+" has a trip to "+Location+propForm.get("form_eng_meet_1")+MeetingDate+propForm.get("form_eng_7");
			form3 = propForm.get("form_eng_8");
			columnfooter = propForm.get("form_eng_9")+"\n"+propForm.get("form_eng_10");
			footer = propFooter.get("footer_1")+"\n"+propFooter.get("footer_2")+"\n"+propFooter.get("footer_3");
			
		
			
			paramCondition = new HashMap<String, Object>();
//			paramCondition.put("current_date", JDate.getDateDD_MM_YYY());
//			paramCondition.put("Running_Number", numberAsString);
//			paramCondition.put("AVG_Amount", formatter.format(Double.parseDouble(avg_format)));
//			paramCondition.put("thai_form", thaitext);
			paramCondition.put("title", title);
			paramCondition.put("header", header);
			paramCondition.put("currenttime", datetime);
			paramCondition.put("columnheader", columnheader);
			paramCondition.put("detail", form);
			paramCondition.put("detail2", form2);
			paramCondition.put("detail3", form3);
			paramCondition.put("columnfooter", columnfooter);
			paramCondition.put("footer", footer);
			paramCondition.put("doctor_name", arrData.get(0).get("NAME_ENG"));
//			paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
			paramConditionNameReport.put(absoluteDiskPath+"IncomeCerificateEng.jasper", paramCondition);
			if(type.equals("1")) {
			genReport.viewPDFReport(paramConditionNameReport, "", true , response);
			}
			else {
				bos = genReport.generateReport(paramConditionNameReport,"", true);	
				message  = sentEmail.SendSingleMailPdfFile(bos, propEmailData.get("sender_emails.0"), doctorCode, "03",propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
			}
			break;
		case "06" : 
			try {
				arrData = vaDetail.getGenerateReportEng(doctorCode, startYYYY, startMM, endMM, endYYYY);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			getrun = arrData.get(0).get("RUNNING_NUMBER");
			numberAsString = String.format ("%03d", Integer.parseInt(getrun));
		
			getavg = arrData.get(0).get("AVG_AMT");
			avg_format = String.format ("%.0f", Float.parseFloat(getavg));
			set_avg_format = ((int)(Math.round( Integer.parseInt(avg_format) / 10.0) * 10));
			engtext = ConvertToString.convert(set_avg_format);
			
//			DateFormat.format(arrData.get(0).get("FROM_DATE"))
			try {
				datetimeEng = DateFormat.format(datedb.parse(arrData.get(0).get("FROM_DATE")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(MeetingName.isEmpty()) {
				MeetingName = "";
			}
			else {
				MeetingName = MeetingName+", ";
			}

			title = propTitle.get("title_1")+"\n"+propTitle.get("title_2")+"\n"+propTitle.get("title_3")+"\n"+propTitle.get("title_4")+"\n"+propTitle.get("title_5");
			header = propHeader.get("header_1")+"\n"+propHeader.get("header_2")+numberAsString+"/"+arrData.get(0).get("batch_date").substring(2, 4);
			columnheader = propColumnHeader.get("columnheader_1");
			form = " "+propForm.get("form_eng_1")+datetimeEng+" "+propForm.get("form_eng_2")
				   +arrData.get(0).get("POSITION")+" "+propForm.get("form_eng_3")+arrData.get(0).get("DESCRIPTION")+" "+propForm.get("form_eng_4")+formatter.format(set_avg_format)+" ("+engtext+" Bhat Only).";
//			form2 = propForm.get("form_eng_8");
			form2 = arrData.get(0).get("NAME_ENG")+" has a meeting in "+MeetingName+Location+propForm.get("form_eng_meet_1")+MeetingDate+propForm.get("form_eng_7");
			form3 = propForm.get("form_eng_8");
			columnfooter = propForm.get("form_eng_9")+"\n"+propForm.get("form_eng_10");
			footer = propFooter.get("footer_1")+"\n"+propFooter.get("footer_2")+"\n"+propFooter.get("footer_3");
			
		
			
			paramCondition = new HashMap<String, Object>();
//			paramCondition.put("current_date", JDate.getDateDD_MM_YYY());
//			paramCondition.put("Running_Number", numberAsString);
//			paramCondition.put("AVG_Amount", formatter.format(Double.parseDouble(avg_format)));
//			paramCondition.put("thai_form", thaitext);
			paramCondition.put("title", title);
			paramCondition.put("header", header);
			paramCondition.put("currenttime", datetime);
			paramCondition.put("columnheader", columnheader);
			paramCondition.put("detail", form);
			paramCondition.put("detail2", form2);
			paramCondition.put("detail3", form3);
			paramCondition.put("columnfooter", columnfooter);
			paramCondition.put("footer", footer);
			paramCondition.put("doctor_name", arrData.get(0).get("NAME_ENG"));
//			paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
			paramConditionNameReport.put(absoluteDiskPath+"IncomeCerificateEng.jasper", paramCondition);
			if(type.equals("1")) {
			genReport.viewPDFReport(paramConditionNameReport, "", true , response);
			}
			else {
				bos = genReport.generateReport(paramConditionNameReport,"", true);	
				message  = sentEmail.SendSingleMailPdfFile(bos, propEmailData.get("sender_emails.0"), doctorCode, "03",propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
			}
			break;
			
		case "07" :
			try {
				arrData = vaDetail.getGenerateReportEng(doctorCode, startYYYY, startMM, endMM, endYYYY);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			getrun = arrData.get(0).get("RUNNING_NUMBER");
			numberAsString = String.format ("%03d", Integer.parseInt(getrun));
		
			getavg = arrData.get(0).get("AVG_AMT");
			avg_format = String.format ("%.0f", Float.parseFloat(getavg));
			set_avg_format = ((int)(Math.round( Integer.parseInt(avg_format) / 10.0) * 10));
			engtext = ConvertToString.convert(set_avg_format);
			
//			DateFormat.format(arrData.get(0).get("FROM_DATE"))
			try {
				datetimeEng = DateFormat.format(datedb.parse(arrData.get(0).get("FROM_DATE")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(MeetingName.isEmpty()) {
				MeetingName = "";
			}
			else {
				MeetingName = MeetingName+", ";
			}
			
			title = propTitle.get("title_1")+"\n"+propTitle.get("title_2")+"\n"+propTitle.get("title_3")+"\n"+propTitle.get("title_4")+"\n"+propTitle.get("title_5");
			header = propHeader.get("header_1")+"\n"+propHeader.get("header_2")+numberAsString+"/"+arrData.get(0).get("batch_date").substring(2, 4);
			columnheader = propColumnHeader.get("columnheader_1");
			form = " "+propForm.get("form_eng_1")+datetimeEng+" "+propForm.get("form_eng_2")
				   +arrData.get(0).get("POSITION")+" "+propForm.get("form_eng_3")+arrData.get(0).get("DESCRIPTION")+" "+propForm.get("form_eng_4")+formatter.format(set_avg_format)+" ("+engtext+" Bhat Only).";
//			form2 = propForm.get("form_eng_8");
			form2 = arrData.get(0).get("NAME_ENG")+" is going to attend "+MeetingName+Location+propForm.get("form_eng_meet_1")+MeetingDate+propForm.get("form_eng_7");
			form3 = propForm.get("form_eng_8");
			columnfooter = propForm.get("form_eng_9")+"\n"+propForm.get("form_eng_10");
			footer = propFooter.get("footer_1")+"\n"+propFooter.get("footer_2")+"\n"+propFooter.get("footer_3");
			
		
			
			paramCondition = new HashMap<String, Object>();
//			paramCondition.put("current_date", JDate.getDateDD_MM_YYY());
//			paramCondition.put("Running_Number", numberAsString);
//			paramCondition.put("AVG_Amount", formatter.format(Double.parseDouble(avg_format)));
//			paramCondition.put("thai_form", thaitext);
			paramCondition.put("title", title);
			paramCondition.put("header", header);
			paramCondition.put("currenttime", datetime);
			paramCondition.put("columnheader", columnheader);
			paramCondition.put("detail", form);
			paramCondition.put("detail2", form2);
			paramCondition.put("detail3", form3);
			paramCondition.put("columnfooter", columnfooter);
			paramCondition.put("footer", footer);
			paramCondition.put("doctor_name", arrData.get(0).get("NAME_ENG"));
//			paramCondition.put("SUBREPORT_DIR", absoluteDiskPath);
			paramConditionNameReport.put(absoluteDiskPath+"IncomeCerificateEng.jasper", paramCondition);
			if(type.equals("1")) {
			genReport.viewPDFReport(paramConditionNameReport, "", true , response);
			}
			else {
				bos = genReport.generateReport(paramConditionNameReport,"", true);	
				message  = sentEmail.SendSingleMailPdfFile(bos, propEmailData.get("sender_emails.0"), doctorCode, "03",propEmailData.get("sender_emails.0"),propPassWordData.get("sender_pwds.0"));
			}
			break;
		}
//		System.out.print("hello");
//		vaDetail.runningNumber();
	}

}
