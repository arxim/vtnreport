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
import net.sf.jasperreports.engine.JasperReport;


public class PrepareFileToSendMailService {

	// TaxLetter406.jasper
	public ByteArrayOutputStream PrepareTaxLetter406(ArrayList<HashMap<String, String>> doctorData,
			JasperReport jasperReport, InputStream jasperStream, HttpServletResponse response,String term) throws JRException, IOException, SQLException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = doctorData.get(0).get("PASS_ENCRYPT");
		
		Map<String, Object> params = new HashMap<>();
		params.put("hospital_code",doctorData.get(0).get("HOSPITAL_CODE"));
		params.put("doctor_code", doctorData.get(0).get("DOCTOR_CODE"));
		params.put("term",term);
		params.put("year",doctorData.get(0).get("YYYY"));

		bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","",password);

		return bos;
	}
	
	// PaymentVoucher.jasper
	public ByteArrayOutputStream PreparePaymentVoucher(ArrayList<HashMap<String, String>> doctorData,
			JasperReport jasperReport, InputStream jasperStream, HttpServletResponse response) throws JRException, IOException, SQLException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JasperBuilderService voJasperBuilder = new JasperBuilderService();
		String password = doctorData.get(0).get("PASS_ENCRYPT");
		
		Map<String, Object> params = new HashMap<>();
		params.put("from_doctor", doctorData.get(0).get("DOCTOR_CODE"));
		params.put("to_doctor",doctorData.get(0).get("DOCTOR_CODE"));
		params.put("month",doctorData.get(0).get("MM"));
		params.put("year",doctorData.get(0).get("YYYY"));

		bos = voJasperBuilder.jasperBuilderPdfEncrypt(jasperStream, jasperReport, response, params, "application/pdf","",password);

		return bos;
	}

	// ExpenseDetail.jasper
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


}
