package com.scap.vtnreport.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.scap.vtnreport.utils.JDate;

public class PrepareFileToSendMailService {

	// TaxLetter406.jasper
	public ByteArrayOutputStream PrepareTaxLetter406(ArrayList<HashMap<String, String>> doctorData,
			JasperReport jasperReport, InputStream jasperStream, HttpServletResponse response,String term,String signature,String printDate) throws JRException, IOException, SQLException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = doctorData.get(0).get("PASS_ENCRYPT");
		
		Map<String, Object> params = new HashMap<>();
		params.put("hospital_code",doctorData.get(0).get("HOSPITAL_CODE"));
		params.put("doctor_code", doctorData.get(0).get("DOCTOR_CODE"));
		params.put("term",term);
		params.put("yyyy",doctorData.get(0).get("YYYY"));
		params.put("signature", signature);
		params.put("print_date", printDate);

		bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","",password);

		return bos;
	}
	
	// PaymentVoucher.jasper
	public ByteArrayOutputStream PreparePaymentVoucher(ArrayList<HashMap<String, String>> doctorData,
			JasperReport jasperReport, InputStream jasperStream, HttpServletResponse response,String absoluteDiskPath,int month, int year) throws JRException, IOException, SQLException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = doctorData.get(0).get("PASS_ENCRYPT");

		String to_date = JDate.getLastDayOfMonth(year, month);
		
		Map<String, Object> params = new HashMap<>();
		params.put("from_doctor", doctorData.get(0).get("DOCTOR_CODE"));
		params.put("to_doctor",doctorData.get(0).get("DOCTOR_CODE"));
		params.put("month",doctorData.get(0).get("MM"));
		params.put("year",doctorData.get(0).get("YYYY"));
		params.put("from_date","00000000");
		params.put("to_date",to_date);
		params.put("SUBREPORT_DIR",absoluteDiskPath);

		bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","",password);

		return bos;
	}

	// RevenueDetail.jasper
	public ByteArrayOutputStream PrepareSummaryRevenueByDetail(ArrayList<HashMap<String, String>> doctorData,
			JasperReport jasperReport, InputStream jasperStream, HttpServletResponse response) throws JRException, IOException, SQLException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = doctorData.get(0).get("PASS_ENCRYPT");
		
		Map<String, Object> params = new HashMap<>();
		params.put("hospital_code",doctorData.get(0).get("HOSPITAL_CODE"));
		params.put("from_doctor", doctorData.get(0).get("DOCTOR_CODE"));
		params.put("to_doctor",doctorData.get(0).get("DOCTOR_CODE"));
		params.put("month",doctorData.get(0).get("MM"));
		params.put("year",doctorData.get(0).get("YYYY"));
		params.put("doctor_category","%%");
		params.put("doctor_department","%%");
		params.put("order_item", "%%");
		params.put("order_item_category","%%");

		bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","",password);

		return bos;
	}
	
	// ExpenseDetail.jasper
	public ByteArrayOutputStream PrepareExpenseDetail(ArrayList<HashMap<String,String>> doctorData,JasperReport jasperReport,InputStream jasperStream,HttpServletResponse response) throws Exception {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = doctorData.get(0).get("PASS_ENCRYPT");
		
		Map<String, Object> params = new HashMap<>();
		params.put("hospital_code",doctorData.get(0).get("HOSPITAL_CODE"));
		params.put("from_doctor", doctorData.get(0).get("DOCTOR_CODE"));
		params.put("to_doctor",doctorData.get(0).get("DOCTOR_CODE"));
		params.put("month",doctorData.get(0).get("MM"));
		params.put("year",doctorData.get(0).get("YYYY"));
		params.put("doctor_category","%%");
		params.put("doctor_department","%%");
		params.put("expense_sign", "%%");
		params.put("expense_account_code","%%");
		params.put("expense_code", "%%");

		bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","",password);

		return bos;
	}

	// UnpaidDetail.jasper
	public ByteArrayOutputStream PrepareSummaryDFUnpaidByDetailAsOfDate(ArrayList<HashMap<String, String>> arrData, JasperReport jasperReport,
			InputStream jasperStream, HttpServletResponse response,int month,int year) throws Exception{

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = arrData.get(0).get("PASS_ENCRYPT");
		String to_date = JDate.getLastDayOfMonth(year, month);
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("from_date","00000000");
		params.put("to_date",to_date);
		params.put("doctor",arrData.get(0).get("DOCTOR_CODE"));
		params.put("hospital_code",arrData.get(0).get("HOSPITAL_CODE"));
		params.put("as_of_date","%%");
		params.put("department_code","%%");
		params.put("payor_code","%%");


		bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","",password);

		return bos;
	}
	
	// Merge PDF 4 file For Send Mail
	public ByteArrayOutputStream PrepareMergePayment(ArrayList<HashMap<String, String>> arrData, JasperReport jasperReport1,JasperReport jasperReport2,JasperReport jasperReport3,JasperReport jasperReport4,
			InputStream jasperStream1,InputStream jasperStream2,InputStream jasperStream3,InputStream jasperStream4, HttpServletResponse response,int month,int year,String absoluteDiskPath) throws Exception{

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = arrData.get(0).get("PASS_ENCRYPT");
		String to_date = JDate.getLastDayOfMonth(year, month);
		
		String from_doctor = arrData.get(0).get("DOCTOR_CODE");
		String to_doctor = arrData.get(0).get("DOCTOR_CODE");
		String mm  = arrData.get(0).get("MM");
		String yyyy = arrData.get(0).get("YYYY");
		String hospitalCode = arrData.get(0).get("HOSPITAL_CODE");
		
		PrepareFileToJasperPrint jasperPrint = new PrepareFileToJasperPrint();
		
		JasperPrint jpPaymentVoucherReport = jasperPrint.PaymentVoucherReport(jasperReport1, from_doctor, to_doctor, mm, yyyy, to_date, absoluteDiskPath);
		JasperPrint jpSummaryRevenueByDetail = jasperPrint.SummaryRevenueByDetail(jasperReport2, hospitalCode, from_doctor, to_doctor, mm, yyyy);
		JasperPrint jpExpenseDetail = jasperPrint.ExpenseDetail(jasperReport3, hospitalCode, from_doctor, to_doctor, mm, yyyy);
		JasperPrint SummaryDFUnpaidByDetailAsOfDate = jasperPrint.SummaryDFUnpaidByDetailAsOfDate(jasperReport4, to_date, to_doctor, hospitalCode);

		bos = voJasperBuilder.jasperBuilderPdfEncryptMergePdf(jpPaymentVoucherReport,jpSummaryRevenueByDetail,jpExpenseDetail,SummaryDFUnpaidByDetailAsOfDate, "application/pdf","",password);
		return bos;
	}

}
