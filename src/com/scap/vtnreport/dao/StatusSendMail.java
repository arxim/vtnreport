package com.scap.vtnreport.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scap.vtnreport.utils.DbConnector;

public class StatusSendMail {
	
	public static void SendMailPaymentSuccess(String hospitalCode, String doctorCode,String mm,String yyyy) throws SQLException{
		PreparedStatement ps = null;
		
		String SQL ="UPDATE PAYMENT_MONTHLY "
		           + "  SET "
		           + "      STATUS_MODIFY = 'T' "
		           + "WHERE DOCTOR_CODE = ? "
		           + " AND HOSPITAL_CODE = ? "
		           + " AND MM = ? "
		           + " AND YYYY = ? ";
		try (Connection conn = DbConnector.getDBConnection()) {
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, doctorCode);
			ps.setString(2, hospitalCode);
			ps.setString(3, mm);
			ps.setString(4, yyyy);
			
			ps.executeQuery();
		} catch (Exception e) {
			
		} finally {
			if (ps != null)
				ps.close();
		}
	}
	
	
	public static void SendMailTax406Success(String hospitalCode, String doctorCode,String term,String yyyy) throws SQLException{
		PreparedStatement ps = null;
		
		String SQL ="UPDATE SUMMARY_TAX_406 "
		           + "  SET "
		           + "      STATUS_MODIFY = 'T' "
		           + "WHERE DOCTOR_CODE = ? "
		           + " AND HOSPITAL_CODE = ? "
		           + " AND MM = ? "
		           + " AND YYYY = ? ";
		try (Connection conn = DbConnector.getDBConnection()) {
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, doctorCode);
			ps.setString(2, hospitalCode);
			ps.setString(3, term);
			ps.setString(4, yyyy);
			
			ps.executeQuery();
		} catch (Exception e) {
			
		} finally {
			if (ps != null)
				ps.close();
		}
	}

}
