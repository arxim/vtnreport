package com.scap.vtnreport.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.PdfWriter;
import com.scap.vtnreport.utils.DbConnector;
import com.scap.vtnreport.utils.ReadProperties;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class ReportPDFBuilderService {
	
/*	public ByteArrayOutputStream generatePDFReport(Map<String,Map<String, Object>> paramCondition){
		return generatePDFReport(paramCondition);
	}*/
	
	public void viewPDFReport(Map<String,Map<String, Object>> paramCondition,String password,boolean isPrint,HttpServletResponse response){
		Connection conn = null;
		List<String> reportName = new ArrayList<String>(paramCondition.keySet());
		ArrayList<JasperPrint> listJasperPrint = new ArrayList<JasperPrint>();
		JRPdfExporter exporter = new JRPdfExporter();
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		conn = DbConnector.getDBConnection();
		OutputStream out = null;
		response.setContentType("application/pdf");
		System.out.println(paramCondition.size());
		
		for (int i = 0; i < paramCondition.size(); i++) {
			System.out.println("file "+reportName.get(i));
			System.out.println("param "+paramCondition.get(reportName.get(i)));
			System.out.println("con "+conn);
			try {
				listJasperPrint.add(JasperFillManager.fillReport(reportName.get(i), paramCondition.get(reportName.get(i)), conn));
				
				response.setHeader("Content-disposition", "inline; filename=" + reportName.get(i) + ".pdf");
				out = response.getOutputStream();
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			
			exporter.setExporterInput(SimpleExporterInput.getInstance(listJasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			
			if(password != "" ||  password == null) {
				exportConfig.set128BitKey(true);
				exportConfig.setUserPassword(password);
				exportConfig.setOwnerPassword(password);
				exportConfig.setEncrypted(true);
			}else{}
			
			if(isPrint){
				exportConfig.setPermissions(PdfWriter.ALLOW_PRINTING);
				exportConfig.setPermissions(PdfWriter.ALLOW_COPY);
			}else {
				exportConfig.setEncrypted(true);
				exportConfig.set128BitKey(true);
				exportConfig.setOwnerPassword("c2NhcEAxMjM0"); // Base64 Encode
				exportConfig.setPermissions(PdfWriter.ALLOW_COPY);
			}
			
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();
			
			System.out.println("View report success");
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} 
		
	}
	
	public ByteArrayOutputStream generateReport(Map<String,Map<String, Object>> paramCondition,String password,boolean isPrint){
		Connection conn = null;
		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
		List<String> reportName = new ArrayList<String>(paramCondition.keySet());
		ArrayList<JasperPrint> listJasperPrint = new ArrayList<JasperPrint>();
		JRPdfExporter exporter = new JRPdfExporter();
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		//get connection
		conn = DbConnector.getDBConnection();
		for (int i = 0; i < paramCondition.size(); i++) {
			//System.out.println("file "+reportName.get(i));
			//System.out.println("param "+paramCondition.get(reportName.get(i)));
			//System.out.println("con "+conn);
			try {
				listJasperPrint.add(JasperFillManager.fillReport(reportName.get(i), paramCondition.get(reportName.get(i)), conn));
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		
		try {
			exporter.setExporterInput(SimpleExporterInput.getInstance(listJasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
			
			if(password != "" ||  password == null) {
				exportConfig.set128BitKey(true);
				exportConfig.setUserPassword(password);
				exportConfig.setOwnerPassword(password);
				exportConfig.setEncrypted(true);
			}else{}
			
			if(isPrint){
				exportConfig.setPermissions(PdfWriter.ALLOW_PRINTING);
				exportConfig.setPermissions(PdfWriter.ALLOW_COPY);
			}else {
				exportConfig.setEncrypted(true);
				exportConfig.set128BitKey(true);
				exportConfig.setOwnerPassword("c2NhcEAxMjM0"); // Base64 Encode
				exportConfig.setPermissions(PdfWriter.ALLOW_COPY);
			}
			
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();
			pdfOutputStream.close();
			System.out.println("pdfOutputStream create success");
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} 
		
		
		
		return pdfOutputStream;
	}
	
	public static void main (String arg[]){
	
	}
}
