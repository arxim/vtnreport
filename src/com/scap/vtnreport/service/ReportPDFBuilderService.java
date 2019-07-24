package com.scap.vtnreport.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.pdf.PdfWriter;
import com.scap.vtnreport.utils.DbConnector;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class ReportPDFBuilderService {
	
	public ByteArrayOutputStream generatePDFReport(Map<String,Map<String, Object>> params){
		return generatePDFReport(params);
	}
	
	public ByteArrayOutputStream viewPDFReport(Map<String,Map<String, Object>> params){
		return generatePDFReport(params);
	}
	
	private ByteArrayOutputStream generateReport(Map<String,Map<String, Object>> params){
		ByteArrayOutputStream a = null;
		return a;
	}
	
	public static void main (String arg[]){
	}
}
