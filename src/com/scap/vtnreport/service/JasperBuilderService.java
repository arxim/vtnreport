package com.scap.vtnreport.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.PdfWriter;
import com.scap.vtnreport.utils.DbConnector;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class JasperBuilderService {

	public ByteArrayOutputStream jasperBuilderPdfEncrypt(InputStream jasperStream, JasperReport jasperReport,
			HttpServletResponse response, Map<String, Object> params, String contentType, String vaFilesName,String password)
			throws JRException, IOException, SQLException {
		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
		Connection conn = null;
		try {
			// get connecttion
			 conn = DbConnector.getDBConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setEncrypted(true);
			configuration.set128BitKey(true);
			configuration.setUserPassword(password);
			configuration.setOwnerPassword(password);
			configuration.setPermissions(PdfWriter.ALLOW_COPY);
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			// close Stream
			jasperStream.close();
			pdfOutputStream.close();
			// Sentmail(pdfOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return pdfOutputStream;
	}

	public void jasperBuilder(InputStream jasperStream, JasperReport jasperReport, HttpServletResponse response,
			Map<String, Object> params, String contentType, String vaFilesName,String permission)
			throws JRException, IOException, SQLException {

		Connection conn = null;
		try {
			// get connecttion
			DbConnector con = new DbConnector();
			conn = con.getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

			response.setContentType(contentType);
			response.setHeader("Content-disposition", "inline; filename=" + vaFilesName + ".pdf");
			OutputStream out = response.getOutputStream();
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			
			 // permission Y = allow print	 
			if(permission.equals("Y")){
				configuration.setPermissions(PdfWriter.ALLOW_COPY); // Allow Copy
				configuration.setPermissions(PdfWriter.ALLOW_PRINTING); // Allow Printing
			}else{
				configuration.setEncrypted(true);
				configuration.set128BitKey(true);
				configuration.setOwnerPassword("c2NhcEAxMjM0"); // Base64 Encode
				configuration.setPermissions(PdfWriter.ALLOW_COPY);
			}
			
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			// close Stream
			jasperStream.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	// Merge PDF 4 file View Report
	public void jasperBuilderMergePdf(JasperPrint jasperPrint1, JasperPrint jasperPrint2, JasperPrint jasperPrint3,
			JasperPrint jasperPrint4, HttpServletResponse response, String contentType, String vaFilesName,
			String permission) throws JRException, IOException, SQLException {
		try {

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint1);
			list.add(jasperPrint2);
			list.add(jasperPrint3);
			list.add(jasperPrint4);

			response.setContentType(contentType);
			response.setHeader("Content-disposition", "inline; filename=" + vaFilesName + ".pdf");
			OutputStream out = response.getOutputStream();

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(SimpleExporterInput.getInstance(list));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();

			// permission Y = allow print
			if (permission.equals("Y")) {
				configuration.setPermissions(PdfWriter.ALLOW_COPY); // Allow Copy
				configuration.setPermissions(PdfWriter.ALLOW_PRINTING); // Allow Printing
			} else {
				configuration.setEncrypted(true);
				configuration.set128BitKey(true);
				configuration.setOwnerPassword("c2NhcEAxMjM0"); // Base64 Encode
				configuration.setPermissions(PdfWriter.ALLOW_COPY);
			}

			exporter.setConfiguration(configuration);
			exporter.exportReport();

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// Merge PDF 4 file Sent Email
	
	public ByteArrayOutputStream jasperBuilderPdfEncryptMergePdf(JasperPrint jasperPrint1,JasperPrint jasperPrint2,JasperPrint jasperPrint3,JasperPrint jasperPrint4, String contentType, String vaFilesName,
			String password) throws JRException, IOException, SQLException {
		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
		try {

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint1);
			list.add(jasperPrint2);
			list.add(jasperPrint3);
			list.add(jasperPrint4);
			

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(SimpleExporterInput.getInstance(list));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setEncrypted(true);
			configuration.set128BitKey(true);
			configuration.setUserPassword(password);
			configuration.setOwnerPassword(password);
			configuration.setPermissions(PdfWriter.ALLOW_COPY);
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			pdfOutputStream.close();
			// Sentmail(pdfOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdfOutputStream;
	}

}
