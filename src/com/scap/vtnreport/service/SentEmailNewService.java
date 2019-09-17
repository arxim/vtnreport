package com.scap.vtnreport.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.scap.vtnreport.dao.GetDoctorDao;
import com.scap.vtnreport.dao.StatusSendMail;
import com.scap.vtnreport.utils.JDate;
import com.scap.vtnreport.utils.ReadProperties;



public class SentEmailNewService implements Job{
	
	private String message;


	public String SendSingleMailPdfFile(ByteArrayOutputStream pdfStream,String mail,String doctor,String report,String auth_email,String auth_password) {
		ReadProperties prop = new ReadProperties();
		Map<String, String>  propData = prop.getDataReadPropertiesFile("servermail.properties");
		//String auth_host = propData.get("auth_host");
		//String auth_port = propData.get("auth_port");
		//String auth_email = propData.get("auth_email");
		//String auth_password = propData.get("auth_password");
		String bcc_email = auth_email;//propData.get("bcc_email");
		String subject="",body="",msg = "",pdf_name="";

		Properties props = new Properties();
		//props.put("mail.smtp.host", auth_host);
		//props.put("mail.smtp.starttls.enable","true");
		//props.put("mail.smtp.socketFactory.port", auth_port);
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", auth_port);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		
		if(report.equals("01")) {
			subject = propData.get("subject_tax_letter_406");
			body = propData.get("body_tax_letter_406");
			pdf_name="TaxLetter406.pdf";
		}else if(report.equals("02")|| report.equals("03")|| report.equals("04")) {
			subject = propData.get("subject_payment");
			body = propData.get("body_payment");
			pdf_name="DF_Payment_Report.pdf";
		}else{}
		
		
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(auth_email, auth_password);
			}
		});
		Message message = new MimeMessage(mailSession);

		try {
			message.setFrom(new InternetAddress(auth_email));// From
			/*** Recipient ***/
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail)); // To
			if (!bcc_email.equals("")) {
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc_email)); // BCC
			}
			message.setSubject(subject + " " + doctor);
			
			System.out.println("Before Attachment ==>"+JDate.getTime());
			DataSource aAttachment = new ByteArrayDataSource(pdfStream.toByteArray(), "application/pdf");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(aAttachment));
			messageBodyPart.setFileName(pdf_name);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			
			System.out.println("Before Attachment ==>"+JDate.getTime());
			
			Transport.send(message);
			
			msg = "PASS";
			System.out.println("Mail Send Successfully.=>"+JDate.getTime());
			
			
			
		} catch (MessagingException e) {
			msg = "FAIL";
			e.printStackTrace();
		} 
		
		
		return  msg;
	}
	
	private String sendMailJob(String hospitalCode,String yyyy,String mm,String absoluteDiskPath,String report) {
		System.out.println("sentMailJob --->>> Time is " + new Date());
		ByteArrayOutputStream bosMergePdfs = null;
		try {
			ReportPDFBuilderService prepareFile = new ReportPDFBuilderService();
			SentEmailNewService sentEmail = new SentEmailNewService();
			ReadProperties prop4 = new ReadProperties();
			Map<String, String>  propEmailData4 = prop4.getPropertiesData("servermail.properties", "sender_emails");
			Map<String, String>  propPassWordData4 = prop4.getPropertiesData("servermail.properties", "sender_pwds");
			Map<String, String>  props = prop4.getDataReadPropertiesFile("servermail.properties");
			int limit_send = Integer.parseInt(props.get("limit_send"));
			int j=0 ,i=0;
			ArrayList<HashMap<String, String>> arrData = null;
			Map<String, Map<String, Object>> paramConditionNameReport = new HashMap<String, Map<String, Object>>();
			Map<String, Object> paramCondition ;
			String list_report ="",message = "";;
			int month = Integer.parseInt(mm);
			int year = Integer.parseInt(yyyy);
			String to_date = JDate.getLastDayOfMonth(year, month);
			
			System.out.println(propEmailData4);
			for(i=0;i< propEmailData4.size();i++) {
				if(i==propEmailData4.size()) {break;}else {}
				
				System.out.println(i+"   "+propEmailData4.get("sender_emails."+i));
				System.out.println(i+"   "+propPassWordData4.get("sender_pwds."+i));
				
				GetDoctorDao vaEmail = new GetDoctorDao();
				arrData = vaEmail.getAllDoctorSendEmailPayment(hospitalCode, yyyy, mm);
				if(arrData.size()>0) {
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
				}else {message = "PASS";}
			}
			
			
		} catch (Exception e) {
			message = "FAIL";
			e.printStackTrace();
		}
		finally {
			 if (bosMergePdfs != null)
				try {
					bosMergePdfs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return message;
	}
	
	public void SetScheduleSendMail(String dateTime,String hospitalCode,String yyyy,String mm,String absoluteDiskPath,String report) {
		System.out.println("test job");
		JobDetail job1 = JobBuilder.newJob(SentEmailNewService.class)
                .withIdentity("job1", "group1").build();
                
		job1.getJobDataMap().put("hospitalCode", hospitalCode);
		job1.getJobDataMap().put("yyyy", yyyy);
		job1.getJobDataMap().put("mm", mm);
		job1.getJobDataMap().put("absoluteDiskPath", absoluteDiskPath);
		job1.getJobDataMap().put("report", report);
		
		
		Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(dateTime))
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 34 16 10 09 ?"))
                .build();
		try {
			Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
	        scheduler1.scheduleJob(job1, trigger1);
	        
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("test job");
		JobDetail job1 = JobBuilder.newJob(SentEmailNewService.class)
                .withIdentity("job1", "group1").build();
		
		job1.getJobDataMap().put("hospitalCode", "VTN01");
		job1.getJobDataMap().put("yyyy", "2019");
		job1.getJobDataMap().put("mm", "06");
		job1.getJobDataMap().put("absoluteDiskPath", "E:\\vtnReportEPayment\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\vtnreport\\WEB-INF\\JasperReport\\");
		job1.getJobDataMap().put("report", "04");
                
		Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 34 16 10 09 ?"))
                .build();
		
		
		try {
			Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
	        scheduler1.scheduleJob(job1, trigger1);
	        
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
        
	}


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String hospitalCode = dataMap.getString("hospitalCode");
		String yyyy = dataMap.getString("yyyy");
		String mm = dataMap.getString("mm");
		String absoluteDiskPath = dataMap.getString("absoluteDiskPath");
		String report = dataMap.getString("report");
		System.out.println(hospitalCode+"  "+yyyy+"   "+mm+"   "+absoluteDiskPath+"   "+report);
		
		sendMailJob(hospitalCode, yyyy, mm, absoluteDiskPath, report);
		//String report;
		//sendMailJob();
	}


}


