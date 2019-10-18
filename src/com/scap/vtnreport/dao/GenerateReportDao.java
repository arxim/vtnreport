package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.scap.vtnreport.utils.DbConnector;


public class GenerateReportDao {
	
public ArrayList<HashMap<String, String>> getGenerateReport(String doctorCode,String startyyyy,String startmm,String endmm,String endyyyy) throws Exception{
		

	PreparedStatement ps = null;
	ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		
		final String SQL ="SELECT TOP 1 DOCTOR_PROFILE.CODE,NAME_THAI,NAME_ENG,POSITION,SUM(DR_NET_PAID_AMT)/count(DR_NET_PAID_AMT) AS AVG_AMT,RUNNING_NUMBER "
		           + "FROM SUMMARY_MONTHLY "
		           + "LEFT JOIN DOCTOR_PROFILE ON DOCTOR_PROFILE.CODE = SUMMARY_MONTHLY.DOCTOR_CODE "
		           + "LEFT JOIN HOSPITAL ON HOSPITAL.CODE = DOCTOR_PROFILE.HOSPITAL_CODE "
		           + "LEFT JOIN TRN_RUNNING_REPORT ON TRN_RUNNING_REPORT.HOSPITAL_CODE = HOSPITAL.CODE "
		           + "WHERE MM BETWEEN ? AND ? AND YYYY BETWEEN ? AND ? AND DOCTOR_PROFILE.CODE = ? "
		           + "group by DOCTOR_PROFILE.CODE, COMPANY_NAME,NAME_THAI,NAME_ENG,POSITION,FROM_DATE,RUNNING_NUMBER "
		           + "ORDER BY RUNNING_NUMBER DESC";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, startmm);
			ps.setString(2, endmm);
			ps.setString(3, startyyyy);
			ps.setString(4, endyyyy);
			ps.setString(5, doctorCode);
			data = DbConnector.convertArrayListHashMap(ps.executeQuery());
			System.out.println(data);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
		return data;
	}


public ArrayList<HashMap<String, String>> getGenerateReportEng(String doctorCode,String startyyyy,String startmm,String endmm,String endyyyy) throws Exception{
	
	PreparedStatement ps = null;
	ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		
		final String SQL ="SELECT TOP 1 COMPANY_NAME,DOCTOR_PROFILE.CODE,NAME_THAI,NAME_ENG,POSITION,DESCRIPTION,FROM_DATE,SUM(DR_NET_PAID_AMT)/count(DR_NET_PAID_AMT) AS AVG_AMT,RUNNING_NUMBER,BATCH.YYYY as batch_date "
		           + "FROM SUMMARY_MONTHLY "
		           + "LEFT JOIN DOCTOR_PROFILE ON DOCTOR_PROFILE.CODE = SUMMARY_MONTHLY.DOCTOR_CODE "
		           + "LEFT JOIN HOSPITAL ON HOSPITAL.CODE = DOCTOR_PROFILE.HOSPITAL_CODE "
		           + "LEFT JOIN TRN_RUNNING_REPORT ON TRN_RUNNING_REPORT.HOSPITAL_CODE = HOSPITAL.CODE "
				   + "LEFT JOIN DEPARTMENT ON DEPARTMENT.CODE = DOCTOR_PROFILE.DEPARTMENT_CODE "
				   + "LEFT JOIN BATCH ON BATCH.HOSPITAL_CODE = HOSPITAL.CODE "
		           + "WHERE SUMMARY_MONTHLY.MM BETWEEN ? AND ? AND SUMMARY_MONTHLY.YYYY BETWEEN ? AND ? AND DOCTOR_PROFILE.CODE = ? AND BATCH.CLOSE_DATE = '' "
		           + "group by DOCTOR_PROFILE.CODE, COMPANY_NAME,NAME_THAI,NAME_ENG,POSITION,DESCRIPTION,FROM_DATE,RUNNING_NUMBER,BATCH.YYYY "
		           + "ORDER BY RUNNING_NUMBER DESC";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, startmm);
			ps.setString(2, endmm);
			ps.setString(3, startyyyy);
			ps.setString(4, endyyyy);
			ps.setString(5, doctorCode);
			data = DbConnector.convertArrayListHashMap(ps.executeQuery());
			System.out.println(data);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
		return data;
	}


	public void runningNumber() {
		PreparedStatement ps = null;
		
		final String SQL ="INSERT INTO TRN_RUNNING_REPORT(HOSPITAL_CODE) values ('VTN01')";
		try (Connection conn = DbConnector.getDBConnection()) {
			Statement st = conn.createStatement();
			st.executeUpdate(SQL);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
