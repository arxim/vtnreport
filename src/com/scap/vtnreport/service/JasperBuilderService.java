package com.scap.vtnreport.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class JasperBuilderService {

	public ByteArrayOutputStream jasperBuilderPdfEncrypt(InputStream jasperStream, JasperReport jasperReport,
			HttpServletResponse response, Map<String, Object> params, String contentType, String vaFilesName)
			throws JRException, IOException, SQLException {
		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
		Connection conn = null;
		try {
			// get connecttion
			// conn = DBConnector.getDBConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setEncrypted(true);
			configuration.set128BitKey(true);
			configuration.setUserPassword("jasper");
			configuration.setOwnerPassword("reports");
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
			Map<String, Object> params, String contentType, String vaFilesName)
			throws JRException, IOException, SQLException {

		Connection conn = null;
		try {
			// get connecttion
			// ++ conn = DBConnector.getDBConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

			response.setContentType(contentType);
			response.setHeader("Content-disposition", "inline; filename=" + vaFilesName + ".pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);

			// close Stream
			jasperStream.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

}
