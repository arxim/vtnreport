package com.scap.vtnreport.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scap.vtnreport.utils.DbConnector;

public class StatusSendMail {
	
	public static void SendMailSuccess(String hospitalCode, String doctorCdoe,String mm,String yyyy) throws SQLException{
		PreparedStatement ps = null;
		
		String SQL ="UPDATE SUMMARY_MONTHLY "
		           + "  SET "
		           + "      STATUS_MODIFY = 'T' "
		           + "WHERE DOCTOR_CODE = ? "
		           + " AND HOSPITAL_CODE = ? "
		           + " AND MM = ? "
		           + " AND YYYY = ? ";
		try (Connection conn = DbConnector.getDBConnection()) {
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, doctorCdoe);
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

}
